package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LongField extends PresentField {

    private final long value;

    public LongField(long value) {
        super(JsonType.NUMBER);
        this.value = value;
    }

    @Override
    public boolean getBoolean() {
        return value >= 0;
    }

    @Override
    public int getInteger() {
        return Math.toIntExact(value);
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
        return BigInteger.valueOf(getInteger());
    }

    @Override
    public BigDecimal getBigDecimal() {
        return BigDecimal.valueOf(value);
    }

    @Override
    public String getString() {
        return String.valueOf(value);
    }

}
