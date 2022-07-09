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

    private static final String NOT_SUCH_FIELD = "Not such field %s";

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
        return optField(fieldName).orElseThrow(() -> notSuchFieldException(fieldName));
    }

    JsonException notSuchFieldException(String field) {
        final String fullMessage;

        fullMessage = String.format(NOT_SUCH_FIELD, field);
        return new JsonException(fullMessage);
    }

    @Override
    public Optional<JsonField> optField(String fieldName) {
        return Optional.ofNullable(fieldMap.get(fieldName));
    }

    @Override
    public JsonType getType(String fieldName) {
        return optType(fieldName).orElseThrow(() -> notSuchFieldException(fieldName));
    }

    @Override
    public Optional<JsonType> optType(String fieldName) {
        return optField(fieldName).map(JsonField::getType);
    }

    @Override
    public Stream<String> nameStream() {
        return fieldMap.keySet().stream();
    }

    @Override
    public Stream<JsonField> arrayStream(String fieldName) {
        final FilterHelper filterHelper;

        filterHelper = new FilterHelper(fieldName, JsonType.ARRAY);
        return optField(fieldName)
                .filter(filterHelper)
                .map(JsonField::arrayFieldStream)
                .orElseThrow(filterHelper);
    }

    @Override
    public JsonObject getJsonObject(String fieldName) {
        final FilterHelper filterHelper;

        filterHelper = new FilterHelper(fieldName, JsonType.OBJECT);
        return optField(fieldName)
                .filter(filterHelper)
                .map(JsonField::getJsonObject)
                .orElseThrow(filterHelper);
    }

    @Override
    public String getString(final String fieldName) {
        final FilterHelper filterHelper;

        filterHelper = new FilterHelper(fieldName, JsonType.STRING);
        return optField(fieldName)
                .filter(filterHelper)
                .map(JsonField::getString)
                .orElseThrow(filterHelper);
    }

    @Override
    public boolean getBoolean(String fieldName) {
        final FilterHelper filterHelper;

        filterHelper = new FilterHelper(fieldName, JsonType.BOOLEAN);
        return optField(fieldName)
                .filter(filterHelper)
                .map(JsonField::getBoolean)
                .orElseThrow(filterHelper);
    }

    public void tryAdd(String fieldName, JsonField field) {
        if(fieldMap.containsKey(fieldName)) {
            throw new IllegalStateException("The field already exists");
        }
        fieldMap.put(fieldName, field);
    }

}
