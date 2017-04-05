package com.codyengel.modelviewintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codyengel.modelviewintent.actions.Action;
import com.codyengel.modelviewintent.actions.ClickAction;
import com.codyengel.modelviewintent.actions.TextChangeAction;
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