package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

class FalseField extends AbstractField{

    FalseField() {
        super(JsonType.BOOLEAN);
    }

    @Override
    public boolean getBoolean() {
        return false;
    }

    @Override
    public int getInteger() {
        return 0;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public double getDouble() {
        return 0;
    }

    @Override
    public BigInteger getBigInteger() {
        return BigInteger.ZERO;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getString() {
        return "false";
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.of(false);
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.of(0);
    }

    @Override
    public Optional<Long> optLong() {
        return Optional.of(0L);
    }

    @Override
    public Optional<Double> optDouble() {
        return Optional.of(0D);
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        return Optional.of(BigInteger.ZERO);
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        return Optional.of(BigDecimal.ZERO);
    }

    @Override
    public Optional<String> optString() {
        return Optional.of(getString());
    }
}
