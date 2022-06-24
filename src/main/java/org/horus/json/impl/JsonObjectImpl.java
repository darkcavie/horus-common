package org.horus.json.impl;

import org.horus.json.JsonField;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class JsonObjectImpl implements JsonObject {

    private final Map<String, JsonField> fieldMap;

    public JsonObjectImpl() {
        fieldMap = new HashMap<>();
    }

    @Override
    public boolean isField(String fieldName) {
        return false;
    }

    @Override
    public JsonField getField(String fieldName) {
        return null;
    }

    @Override
    public JsonType getType(String fieldName) {
        return null;
    }

    @Override
    public Optional<JsonField> optField(String fieldName) {
        return Optional.empty();
    }

    @Override
    public Optional<JsonType> optType(String fieldName) {
        return Optional.empty();
    }

    @Override
    public Stream<String> nameStream() {
        return null;
    }

    public void tryAdd(String fieldName, JsonField field) {
        if(fieldMap.containsKey(fieldName)) {
            throw new IllegalStateException("The field exist already");
        }
        fieldMap.put(fieldName, field);
    }

}
