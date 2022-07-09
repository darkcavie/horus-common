package org.horus.json.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class LongFieldTest {

    private LongField field;

    @BeforeEach
    void setUp() {
        field = new LongField(-4321L);
    }

    @Test
    void getBoolean() {
        final LongField falseField;

        assertFalse(field.getBoolean());
        falseField = new LongField(1);
        assertTrue(falseField.getBoolean());
    }

    @Test
    void getInteger() {
        assertEquals(-4321, field.getInteger());
    }

    @Test
    void getLong() {
        assertEquals(-4321L, field.getLong());
    }

    @Test
    void getDouble() {
        assertEquals(-4321.0, field.getDouble());
    }

    @Test
    void getBigInteger() {
        assertEquals(BigInteger.valueOf(-4321), field.getBigInteger());
    }

    @Test
    void getBigDecimal() {
        assertEquals(BigDecimal.valueOf(-4321), field.getBigDecimal());
    }

    @Test
    void getString() {
        assertEquals("-4321", field.getString());
    }

    @Test
    void optInteger() {
        assertTrue(field.optInteger().isPresent());
    }

}
