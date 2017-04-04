package com.codyengel.modelviewintent;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author cody
 */
interface MainView {

    Map<String, Observable> getActions();

    Map<String, Consumer> getConsumers();

}
