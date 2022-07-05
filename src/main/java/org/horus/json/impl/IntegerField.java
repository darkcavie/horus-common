package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class IntegerField extends AbstractField {

    private final int value;

    public IntegerField(int value) {
        super(JsonType.NUMBER);
        this.value = value;
    }

    @Override
    public boolean getBoolean() {
        return value >= 0;
    }

    @Override
    public int getInteger() {
        return value;
    }

    @Override
    public long getLong() {
        return value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public BigInteger getBigInteger() {
        return BigInteger.valueOf(value);
    }

    @Override
    public BigDecimal getBigDecimal() {
        return BigDecimal.valueOf(value);
    }

    @Override
    public String getString() {
        return String.valueOf(value);
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.of(getBoolean());
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.of(value);
    }

    @Override
    public Optional<Long> optLong() {
        return Optional.of(getLong());
    }

    @Override
    public Optional<Double> optDouble() {
        return Optional.of(getDouble());
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        return Optional.of(getBigInteger());
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        return Optional.of(getBigDecimal());
    }

    @Override
    public Optional<String> optString() {
        return Optional.of(getString());
    }

}
