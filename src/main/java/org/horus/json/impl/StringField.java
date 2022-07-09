package org.horus.json.impl;

import org.horus.json.JsonType;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

public class StringField extends AbstractField {

    private static final Logger LOG = getLogger(StringField.class);

    private final String value;

    public StringField(String value) {
        super(JsonType.STRING);
        this.value = requireNonNull(value, "Value is mandatory");
    }

    @Override
    public boolean getBoolean() {
        return optBoolean()
                .orElseThrow(() -> notValidTypeValue("boolean", value));
    }

    @Override
    public int getInteger() {
        return Integer.parseInt(value);
    }

    @Override
    public long getLong() {
        return Long.parseLong(value);
    }

    @Override
    public double getDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public BigInteger getBigInteger() {
        return new BigInteger(value);
    }

    @Override
    public BigDecimal getBigDecimal() {
        return new BigDecimal(value);
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public Optional<Boolean> optBoolean() {
        switch(value) {
            case "true": return Optional.of(true);
            case "false": return Optional.of(false);
            default: return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> optInteger() {
        try {
            return Optional.of(value)
                    .map(Integer::valueOf);
        } catch(NumberFormatException ex) {
            LOG.warn("The value [{}] is not a valid integer", value, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> optLong() {
        try {
            return Optional.of(value)
                    .map(Long::valueOf);
        } catch(NumberFormatException ex) {
            LOG.warn("The value [{}] is not a valid long", value, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Double> optDouble() {
        try {
            return Optional.of(value)
                    .map(Double::valueOf);
        } catch(NumberFormatException ex) {
            LOG.warn("The value [{}] is not a valid double", value, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<BigInteger> optBigInteger() {
        try {
            return Optional.of(value)
                    .map(BigInteger::new);
        } catch(NumberFormatException ex) {
            LOG.warn("The value [{}] is not a valid big integer", value, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<BigDecimal> optBigDecimal() {
        try {
            return Optional.ofNullable(value)
                    .map(BigDecimal::new);
        } catch(NumberFormatException ex)  {
            LOG.warn("The value [{}] is not a valid big decimal", value, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> optString() {
        return Optional.ofNullable(value);
    }

}
