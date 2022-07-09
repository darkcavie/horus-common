package org.horus.json.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalFieldTest {

    private BigDecimalField field;

    @BeforeEach
    void setUp() {
        field = new BigDecimalField(BigDecimal.valueOf(1234.5));
    }

    @Test
    void getBoolean() {
        assertTrue(field.getBoolean());
        assertFalse(new BigDecimalField(BigDecimal.ZERO).getBoolean());
        assertFalse(new BigDecimalField(BigDecimal.valueOf(-0.1)).getBoolean());
    }

    @Test
    void getInteger() {
        assertThrows(ArithmeticException.class, field::getInteger);
    }

    @Test
    void getLong() {
        assertThrows(ArithmeticException.class, field::getLong);
        assertEquals(1L, new BigDecimalField(BigDecimal.ONE).getLong());
    }

    @Test
    void getDouble() {
        assertEquals(1234.5, field.getDouble());
    }

    @Test
    void getBigInteger() {
        assertEquals(BigInteger.valueOf(1234), field.getBigInteger());
    }

    @Test
    void getBigDecimal() {
        assertEquals(BigDecimal.valueOf(1234.5), field.getBigDecimal());
    }

    @Test
    void getString() {
        assertEquals("1234.5", field.getString());
    }

}
