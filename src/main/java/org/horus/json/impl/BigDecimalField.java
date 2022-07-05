package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Objects.requireNonNull;

public class BigDecimalField extends PresentField {

    private final BigDecimal value;

    public BigDecimalField(BigDecimal value) {
        super(JsonType.NUMBER);
        this.value = requireNonNull(value, "Null not allowed");
    }

    @Override
    public boolean getBoolean() {
        return value.signum() == 1;
    }

    @Override
    public int getInteger() {
        return value.intValueExact();
    }

    @Override
    public long getLong() {
        return value.longValueExact();
    }

    @Override
    public double getDouble() {
        return value.doubleValue();
    }

    @Override
    public BigInteger getBigInteger() {
        return value.toBigInteger();
    }

    @Override
    public BigDecimal getBigDecimal() {
        return value;
    }

    @Override
    public String getString() {
        return value.toString();
    }

}
