package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonField;
import org.horus.json.JsonType;

import java.util.function.Predicate;
import java.util.function.Supplier;

class FilterHelper implements Predicate<JsonField>, Supplier<JsonException> {

    private static final String NOT_SUCH_FIELD_OF_TYPE = "Not such field %s of type %s";

    private final String fieldName;

    private final JsonType jsonType;

    FilterHelper(final String fieldName, final JsonType jsonType) {
        this.fieldName = fieldName;
        this.jsonType = jsonType;
    }

    @Override
    public boolean test(final JsonField jsonField) {
        return this.jsonType.equals(jsonField.getType());
    }

    @Override
    public JsonException get() {
        final String fullMessage;

        fullMessage = String.format(NOT_SUCH_FIELD_OF_TYPE, fieldName, jsonType.name().toLowerCase());
        return new JsonException(fullMessage);
    }

}
