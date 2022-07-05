package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonField;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class JsonObjectImpl implements JsonObject {

    private static final String NOT_FOUND = "Not found %s";

    private final Map<String, JsonField> fieldMap;

    public JsonObjectImpl() {
        fieldMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isField(String fieldName) {
        return fieldMap.containsKey(fieldName);
    }

    @Override
    public JsonField getField(String fieldName) {
        return optField(fieldName).orElseThrow(() -> createException(NOT_FOUND, fieldName));
    }

    JsonException createException(String message, String field) {
        final String fullMessage;

        fullMessage = String.format(message, field);
        return new JsonException(fullMessage);
    }

    @Override
    public Optional<JsonField> optField(String fieldName) {
        return Optional.ofNullable(fieldMap.get(fieldName));
    }

    @Override
    public JsonType getType(String fieldName) {
        return optType(fieldName).orElseThrow(() -> createException(NOT_FOUND, fieldName));
    }

    @Override
    public Optional<JsonType> optType(String fieldName) {
        return optField(fieldName).map(JsonField::getType);
    }

    @Override
    public Stream<String> nameStream() {
        return fieldMap.keySet().stream();
    }

    public void tryAdd(String fieldName, JsonField field) {
        if(fieldMap.containsKey(fieldName)) {
            throw new IllegalStateException("The field already exists");
        }
        fieldMap.put(fieldName, field);
    }

}
