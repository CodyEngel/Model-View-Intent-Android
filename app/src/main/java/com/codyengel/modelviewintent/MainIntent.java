/*
 * Copyright (c) 2017 Cody Engel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.codyengel.modelviewintent;

import com.codyengel.modelviewintent.actions.Action;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author cody
 */
class MainIntent {

    private List<Observable<Action>> actions;
    private Map<String, Consumer> consumers;
    private MainModel mainModel;

    MainIntent(MainView mainView) {
        actions = mainView.getActions();
        consumers = mainView.getConsumers();
        mainModel = new MainModel();
    }

    @SuppressWarnings("unchecked")
    void start() {
        Observable<Action> actionsObservable = actions.get(0).mergeWith(actions.get(1));

        actionsObservable.subscribe(new DisposingObserver<Action>() {
            @Override
            public void onNext(Action action) {
                switch(action.getType()) {
                    case CLICK:
                        if (MainActivity.BUTTON_IDENTIFIER.equals(action.getActionIdentifier())) {
                            mainModel.resetText();
                        }
                        break;
                    case TEXT_CHANGE:
                        if (MainActivity.EDIT_TEXT_IDENTIFIER.equals(action.getActionIdentifier())) {
                            mainModel.changeText(action.getPayload().get("text_change").toString());
                        }
                        break;
                    default:
                        // nothing
                }
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        });

        mainModel.getObservable().subscribe(new DisposingObserver<MainModel>() {
            @Override
            public void onNext(MainModel changedModel) {
                if (changedModel.wasReset()) {
                    try {
                        consumers.get("EditText").accept(changedModel.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        consumers.get("TextView").accept(changedModel.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}