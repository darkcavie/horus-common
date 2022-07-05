package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class StringField extends AbstractField {

    private final String value;

    public StringField(String value) {
        super(JsonType.STRING);
        this.value = requireNonNull(value, "Value is mandatory");
    }

    @Override
    public boolean getBoolean() {
        return optBoolean().orElseThrow(() -> notValidTypeValue("boolean", value));
    }

    @Override
    public int getInteger() {
        return optInteger().orElseThrow(() -> notValidTypeValue("integer", value));
    }

    @Override
    public long getLong() {
        return optLong().orElseThrow(() -> notValidTypeValue("long", value));
    }

    @Override
    public double getDouble() {
        return optDouble().orElseThrow(() -> notValidTypeValue("double", value));
    }

    @Override
    public BigInteger getBigInteger() {
        return optBigInteger().orElseThrow(() -> notValidTypeValue("big integer", value));
    }

    @Override
    public BigDecimal getBigDecimal() {
        return optBigDecimal().orElseThrow(() -> notValidTypeValue("big decimal", value));
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.of(value)
                .map(Boolean::valueOf);
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.of(value)
                .map(Integer::valueOf);
    }

    @Override
    public Optional<Long> optLong() {
        return Optional.of(value)
                .map(Long::valueOf);
    }

    @Override
    public Optional<Double> optDouble() {
        return Optional.of(value)
                .map(Double::valueOf);
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        return Optional.of(value)
                .map(BigInteger::new);
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        return Optional.of(value)
                .map(BigDecimal::new);
    }

    @Override
    public Optional<String> optString() {
        return Optional.of(value);
    }

}
