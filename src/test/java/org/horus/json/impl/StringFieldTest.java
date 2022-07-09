package org.horus.json.impl;

import org.horus.json.JsonException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class StringFieldTest {

    @Test
    void getBoolean() {
        assertTrue(new StringField("true").getBoolean());
        assertFalse(new StringField("false").getBoolean());
        assertThrows(JsonException.class, new StringField("content")::getBoolean);
    }

    @Test
    void getInteger() {
        assertThrows(NumberFormatException.class, new StringField("content")::getInteger);
        assertEquals(1, new StringField("1").getInteger());
    }

    @Test
    void getLong() {
        assertThrows(NumberFormatException.class, new StringField("content")::getLong);
        assertEquals(1L, new StringField("1").getLong());
    }

    @Test
    void getDouble() {
        assertThrows(NumberFormatException.class, new StringField("content")::getDouble);
        assertEquals(1.0, new StringField("1").getDouble());
    }

    @Test
    void getBigInteger() {
        assertThrows(NumberFormatException.class, new StringField("content")::getBigInteger);
        assertEquals(BigInteger.ONE, new StringField("1").getBigInteger());
    }

    @Test
    void getBigDecimal() {
        assertThrows(NumberFormatException.class, new StringField("content")::getBigDecimal);
        assertEquals(BigDecimal.ONE, new StringField("1").getBigDecimal());
    }

    @Test
    void getString() {
        assertEquals("content", new StringField("content").getString());
    }

    @Test
    void optString() {
        new StringField("content").optString()
                .ifPresentOrElse(s -> assertEquals("content", s), () -> fail("Must value 'content'"));
    }

    @Test
    void optInteger() {
        assertFalse(new StringField("content")
                .optInteger()
                .isPresent());
        new StringField("1")
                .optInteger()
                .ifPresentOrElse(i -> assertEquals(1, i), () -> fail("Must value 1"));
    }

}
