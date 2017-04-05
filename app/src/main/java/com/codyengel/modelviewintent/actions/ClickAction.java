package com.codyengel.modelviewintent.actions;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @author cody
 */

public class ClickAction {

    public static Observable<Action> register(View view, Integer viewIdentifier) {
        return RxView.clicks(view).map(o -> new Action(Action.Type.CLICK, viewIdentifier, new HashMap<>()));
    }

    private ClickAction() {}
}
