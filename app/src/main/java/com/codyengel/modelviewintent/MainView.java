package com.codyengel.modelviewintent;

import com.codyengel.modelviewintent.actions.Action;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author cody
 */
interface MainView {

    List<Observable<Action>> getActions();

    Map<String, Consumer> getConsumers();

}
