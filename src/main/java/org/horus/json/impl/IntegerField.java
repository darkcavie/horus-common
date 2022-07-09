package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IntegerField extends PresentField {

    private final int value;

    public IntegerField(int value) {
        super(JsonType.NUMBER);
        this.value = value;
    }

    @Override
    public boolean getBoolean() {
        return value > 0;
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

}
