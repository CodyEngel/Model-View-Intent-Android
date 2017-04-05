package com.codyengel.modelviewintent.actions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cody
 */

public class Action {

    public enum Type {
        CLICK, TEXT_CHANGE
    }

    private Type type;
    private Integer actionIdentifier;
    private Map<String, Object> payload;

    public Action() {}

    public Action(Type type, Integer actionIdentifier, Map<String, Object> payload) {
        this.type = type;
        this.actionIdentifier = actionIdentifier;
        this.payload = new HashMap<>(payload);
    }

    public Type getType() {
        return type;
    }

    public Integer getActionIdentifier() {
        return actionIdentifier;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }
}
