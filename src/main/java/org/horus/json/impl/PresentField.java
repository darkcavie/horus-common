package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

abstract class PresentField extends AbstractField {

    public PresentField(JsonType type) {
        super(type);
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.of(getBoolean());
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.of(getInteger());
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
