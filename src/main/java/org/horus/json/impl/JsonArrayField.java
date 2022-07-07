package org.horus.json.impl;

import org.horus.json.JsonException;
import org.horus.json.JsonField;
import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

public class JsonArrayField extends AbstractField {

    private static final String BUT_AN_ARRAY = "Not a %s but an array";

    private JsonArray value;

    public JsonArrayField(JsonArray value) {
        super(JsonType.ARRAY);
        this.value = value;
    }

    @Override
    public Stream<JsonField> arrayFieldStream() {
        return value.stream();
    }

    @Override
    public boolean getBoolean() {
        return false;
    }

    @Override
    public int getInteger() {
        throw butAnArrayException("integer");
    }

    private JsonException butAnArrayException(String type) {
        final String message = String.format(BUT_AN_ARRAY, type);
        return new JsonException(message);
    }

    @Override
    public long getLong() {
        throw butAnArrayException("long");
    }

    @Override
    public double getDouble() {
        throw butAnArrayException("double");
    }

    @Override
    public BigInteger getBigInteger() {
        throw butAnArrayException("big integer");
    }

    @Override
    public BigDecimal getBigDecimal() {
        throw butAnArrayException("big decimal");
    }

    @Override
    public String getString() {
        throw butAnArrayException("string");
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
