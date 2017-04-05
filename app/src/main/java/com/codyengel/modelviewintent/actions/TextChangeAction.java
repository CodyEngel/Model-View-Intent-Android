package com.codyengel.modelviewintent.actions;

import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author cody
 */

public class TextChangeAction {

    public static Observable<Action> register(TextView textView, Integer viewIdentifier) {
        return RxTextView.textChanges(textView).map(textChange -> {
            Map<String, Object> payload = new HashMap<>();
            payload.put("text_change", textChange);
            return new Action(Action.Type.TEXT_CHANGE, viewIdentifier, payload);
        });
    }

    private TextChangeAction() {}
}
