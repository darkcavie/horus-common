package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class JsonObjectField extends AbstractField {

    private final String BUT_AN_OBJECT = "Not a %s but an object";

    private final JsonObject value;

    public JsonObjectField(JsonObject value) {
        super(JsonType.OBJECT);
        this.value = requireNonNull(value, "Object value is mandatory");
    }

    @Override
    public JsonObject getJsonObject() {
        return value;
    }


    @Override
    public boolean getBoolean() {
        return true;
    }

    @Override
    public int getInteger() {
        throw buildException("integer");
    }

    JsonException buildException(String type) {
        final String message = String.format(BUT_AN_OBJECT, type);
        return new JsonException(message);
    }

    @Override
    public long getLong() {
        throw buildException("long");
    }

    @Override
    public double getDouble() {
        throw buildException("double");
    }

    @Override
    public BigInteger getBigInteger() {
        throw buildException("big integer");
    }

    @Override
    public BigDecimal getBigDecimal() {
        throw buildException("big decimal");
    }

    @Override
    public String getString() {
        throw buildException("string");
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> optLong() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> optDouble() {
        return Optional.empty();
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        return Optional.empty();
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        return Optional.empty();
    }

    @Override
    public Optional<String> optString() {
        return Optional.empty();
    }

}
