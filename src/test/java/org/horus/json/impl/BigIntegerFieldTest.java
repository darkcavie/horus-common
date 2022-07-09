package org.horus.json.impl;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigIntegerFieldTest {

    @Test
    void getBoolean() {
        assertTrue(new BigIntegerField(BigInteger.ONE).getBoolean());
        assertFalse(new BigIntegerField(BigInteger.ZERO).getBoolean());
    }

    @Test
    void getInteger() {
        final BigInteger value;

        assertEquals(1, new BigIntegerField(BigInteger.ONE).getInteger());
        value = BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.ONE);
        assertThrows(ArithmeticException.class, () -> new BigIntegerField(value).getInteger());
    }

    @Test
    void getLong() {
        final BigInteger value;

        assertEquals(1L, new BigIntegerField(BigInteger.ONE).getLong());
        value = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);
        assertThrows(ArithmeticException.class, () -> new BigIntegerField(value).getLong());
    }


    @Test
    void getString() {
        assertEquals("1", new BigIntegerField(BigInteger.ONE).getString());
    }

}
