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
