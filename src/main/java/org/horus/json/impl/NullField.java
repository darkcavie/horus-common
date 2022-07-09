package org.horus.json.impl;

import org.horus.json.JsonField;
import org.horus.json.JsonObject;
import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

public class NullField implements JsonField {

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public JsonType getType() {
        return null;
    }

    @Override
    public boolean isFieldOfType(JsonType type) {
        return false;
    }

    @Override
    public boolean getBoolean() {
        throw throwNoValue();
    }

    @Override
    public int getInteger() {
        throw throwNoValue();
    }

    @Override
    public long getLong() {
        throw throwNoValue();
    }

    @Override
    public double getDouble() {
        throw throwNoValue();
    }

    @Override
    public BigInteger getBigInteger() {
        throw throwNoValue();
    }

    @Override
    public BigDecimal getBigDecimal() {
        throw throwNoValue();
    }

    @Override
    public String getString() {
        throw throwNoValue();
    }

    @Override
    public JsonObject getJsonObject() {
        throw throwNoValue();
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

    @Override
    public Optional<JsonObject> optJsonObject() {
        return Optional.empty();
    }

    @Override
    public Stream<JsonField> arrayFieldStream() {
        throw throwNoValue();
    }

    private IllegalStateException throwNoValue() {
        return new IllegalStateException("No value");
    }

}
