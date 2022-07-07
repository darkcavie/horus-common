package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonField;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JsonObjectImpl implements JsonObject {

    private static final String NOT_SUCH_FIELD = "Not such field %s";

    private static final String NOT_SUCH_FIELD_OF_TYPE = "Not such field %s of type %s";

    private final Map<String, JsonField> fieldMap;

    private final JsonObjectImpl previous;

    public JsonObjectImpl(JsonObjectImpl previous) {
        fieldMap = new ConcurrentHashMap<>();
        this.previous = previous;
    }

    public JsonObjectImpl() {
        this(null);
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

    public Optional<JsonObjectImpl> optPrevious() {
        return Optional.ofNullable(previous);
    }

    private static class FilterHelper implements Predicate<JsonField>, Supplier<JsonException> {

        private final String fieldName;

        private final JsonType jsonType;

        private FilterHelper(String fieldName, JsonType jsonType) {
            this.fieldName = fieldName;
            this.jsonType = jsonType;
        }

        @Override
        public boolean test(JsonField jsonField) {
            return this.jsonType.equals(jsonField.getType());
        }

        @Override
        public JsonException get() {
            final String fullMessage;

            fullMessage = String.format(NOT_SUCH_FIELD_OF_TYPE, fieldName, jsonType.name().toLowerCase());
            return new JsonException(fullMessage);
        }

    }

}
