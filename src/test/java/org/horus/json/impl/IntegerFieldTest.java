package org.horus.json.impl;

import org.horus.json.JsonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class IntegerFieldTest {

    private IntegerField field;

    @BeforeEach
    void setUp() {
        field = new IntegerField(1234);
    }

    @Test
    void optBoolean() {
        assertTrue(field.optBoolean().isPresent());
        assertFalse(new IntegerField(0).getBoolean());
    }

    @Test
    void optInteger() {
        assertTrue(field.optBoolean().isPresent());
    }

    @Test
    void optLong() {
        assertTrue(field.optLong().isPresent());
    }

    @Test
    void optDouble() {
        assertTrue(field.optDouble().isPresent());
    }

    @Test
    void optBigInteger() {
        assertTrue(field.optBigInteger().isPresent());
    }

    @Test
    void optBigDecimal() {
        assertTrue(field.optBigDecimal().isPresent());
    }

    @Test
    void optString() {
        assertTrue(field.optString().isPresent());
    }

    @Test
    void isNull() {
        assertFalse(field.isNull());
    }

    @Test
    void getType() {
        assertEquals(JsonType.NUMBER, field.getType());
    }

    @Test
    void isFieldOfType() {
        assertTrue(field.isFieldOfType(JsonType.NUMBER));
        assertFalse(field.isFieldOfType(JsonType.STRING));
        assertFalse(field.isFieldOfType(null));
    }

    @Test
    void getJsonObject() {
        assertThrows(IllegalAccessError.class, field::getJsonObject);
    }

    @Test
    void optJsonObject() {
        assertTrue(field.optJsonObject().isEmpty());
    }

    @Test
    void arrayFieldStream() {
        assertThrows(IllegalAccessError.class, field::arrayFieldStream);
    }

    @Test
    void getBoolean() {
        assertTrue(field.getBoolean());
    }

    @Test
    void getInteger() {
        assertEquals(1234, field.getInteger());
    }

    @Test
    void getLong() {
        assertEquals(1234L, field.getLong());
    }

    @Test
    void getDouble() {
        assertEquals(1234.0, field.getDouble());
    }

    @Test
    void getBigInteger() {
        assertEquals(BigInteger.valueOf(1234), field.getBigInteger());
    }

    @Test
    void getBigDecimal() {
        assertEquals(BigDecimal.valueOf(1234), field.getBigDecimal());
    }

    @Test
    void getString() {
        assertEquals("1234", field.getString());
    }

}
