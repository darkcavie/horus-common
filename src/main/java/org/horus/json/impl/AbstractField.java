package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonField;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

abstract class AbstractField implements JsonField {

    private static final String NOT_A_VALID_MESSAGE = "[%s] is not a valid %s value";

    private final JsonType type;

    AbstractField(JsonType type) {
        this.type = requireNonNull(type, "Type is mandatory");
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public JsonType getType() {
        return type;
    }

    @Override
    public boolean isFieldOfType(JsonType type) {
        return this.type == type;
    }

    @Override
    public JsonObject getJsonObject() {
        throw new IllegalAccessError("Not a Json object");
    }

    @Override
    public Optional<JsonObject> optJsonObject() {
        return Optional.empty();
    }

    @Override
    public Stream<JsonField> arrayFieldStream() {
        throw new IllegalAccessError("Not a Json array");
    }

    JsonException notValidTypeValue(String type, String value) {
        final String fullMessage;

        fullMessage = String.format(NOT_A_VALID_MESSAGE, type, value);
        return new JsonException(fullMessage);
    }

}
