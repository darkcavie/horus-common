package org.horus.json.impl;

import org.horus.json.JsonField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class JsonArray {

    private final List<JsonField> array;

    public JsonArray() {
        array = new ArrayList<>();
    }

    public void add(JsonField field) {
        requireNonNull(field);
        array.add(field);
    }

    public Stream<JsonField> stream() {
        return array.stream();
    }

}
