package com.codyengel.modelviewintent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author cody
 */
class MainModel {

    private final String defaultText = "Default Text";

    private PublishSubject<MainModel> modelObservable;

    private String text;

    private boolean wasReset = true;

    MainModel() {
        this.modelObservable = PublishSubject.create();
        resetText();
    }

    Observable<MainModel> getObservable() {
        return modelObservable;
    }

    void changeText(String text) {
        this.wasReset = false;
        this.text = text;
        this.modelObservable.onNext(this);
    }

    void resetText() {
        this.wasReset = true;
        this.text = defaultText;
        this.modelObservable.onNext(this);
    }

    String getText() {
        return text;
    }

    boolean wasReset() {
        return wasReset;
    }
}