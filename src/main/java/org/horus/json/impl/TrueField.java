package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class TrueField extends AbstractField {

    public TrueField() {
        super(JsonType.BOOLEAN);
    }

    @Override
    public boolean getBoolean() {
        return true;
    }

    @Override
    public int getInteger() {
        return 1;
    }

    @Override
    public long getLong() {
        return 1L;
    }

    @Override
    public double getDouble() {
        return 1D;
    }

    @Override
    public BigInteger getBigInteger() {
        return BigInteger.ONE;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return BigDecimal.ONE;
    }

    @Override
    public String getString() {
        return "true";
    }

    @Override
    public Optional<Boolean> optBoolean() {
        return Optional.of(true);
    }

    @Override
    public Optional<Integer> optInteger() {
        return Optional.of(1);
    }

    @Override
    public Optional<Long> optLong() {
        return Optional.of(1L);
    }

    @Override
    public Optional<Double> optDouble() {
        return Optional.of(1D);
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        return Optional.of(BigInteger.ONE);
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        return Optional.of(BigDecimal.ONE);
    }

    @Override
    public Optional<String> optString() {
        return Optional.of(getString());
    }

}
