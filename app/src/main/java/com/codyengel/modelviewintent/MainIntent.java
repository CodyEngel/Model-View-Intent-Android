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

        actionsObservable.subscribe(action -> {
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
        });

        mainModel.getObservable().subscribe(changedModel -> {
            if (changedModel.wasReset()) {
                consumers.get("EditText").accept(changedModel.getText());
            } else {
                consumers.get("TextView").accept(changedModel.getText());
            }
        });
    }
}