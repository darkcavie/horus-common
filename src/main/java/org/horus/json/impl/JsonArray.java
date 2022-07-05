package org.horus.json.impl;

import org.horus.json.JsonField;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class JsonArray {

    private final List<AbstractField> array;

    JsonArray() {
        array = new ArrayList<>();
    }

    public void add(JsonField field) {
        requireNonNull(field);

    }

}
