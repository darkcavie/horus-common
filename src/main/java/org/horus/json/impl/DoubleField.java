package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DoubleField extends PresentField {

    private final double value;

    public DoubleField(double value) {
        super(JsonType.NUMBER);
        this.value = value;
    }

    @Override
    public boolean getBoolean() {
        return value >= 0;
    }

    @Override
    public int getInteger() {
        return Math.toIntExact(getLong());
    }

    @Override
    public long getLong() {
        return Math.round(value);
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
