package org.horus.json.impl;

import org.horus.json.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Objects.requireNonNull;

public class BigIntegerField extends PresentField {

    private final BigInteger value;

    public BigIntegerField(BigInteger value) {
        super(JsonType.NUMBER);
        this.value = requireNonNull(value, "Value is required");
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
        return value;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return new BigDecimal(value);
    }

    @Override
    public String getString() {
        return value.toString();
    }

}
