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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codyengel.modelviewintent.actions.Action;
import com.codyengel.modelviewintent.actions.ClickAction;
import com.codyengel.modelviewintent.actions.TextChangeAction;
import com.codyengel.modelviewintent.utils.DisposableManager;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author cody
 */
public class MainActivity extends AppCompatActivity implements MainView {

    public static final Integer BUTTON_IDENTIFIER = R.id.button;
    public static final Integer EDIT_TEXT_IDENTIFIER = R.id.editText;

    @BindView(R.id.button) Button button;
    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.text) TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new MainIntent(this).start();
    }

    @Override
    protected void onDestroy() {
        DisposableManager.dispose();
        super.onDestroy();
    }

    @Override
    public List<Observable<Action>> getActions() {
        List<Observable<Action>> actions = new ArrayList<>();
        actions.add(ClickAction.register(button, BUTTON_IDENTIFIER));
        actions.add(TextChangeAction.register(editText, EDIT_TEXT_IDENTIFIER));
        return actions;
    }

    @Override
    public Map<String, Consumer> getConsumers() {
        Map<String, Consumer> consumers = new HashMap<>();
        consumers.put("TextView", RxTextView.text(text));
        consumers.put("EditText", RxTextView.text(editText));
        return consumers;
    }
}