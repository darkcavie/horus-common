package org.horus.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonObjectBuilderTest {

    private static final String ANY_THING = "anyThing";

    private static final String INT_FIELD = "intField";

    private static final String LONG_FIELD = "longField";

    private static final String DOUBLE_FIELD = "doubleField";

    private static final String BIG_DECIMAL_FIELD = "bigDecimalField";

    private static final String BIG_INTEGER_FIELD = "bigIntegerField";

    private static final String STRING_FIELD = "stringField";

    private static final String OBJECT_FIELD = "objectField";

    private JsonObjectBuilder builder;

    private JsonObject object;

    @BeforeEach
    void setUp() {
        builder = new JsonObjectBuilder();
        object = null;
    }

    @Test
    void testEmptyObject() {
        object = builder.build();
        assertNotNull(object);
        assertFalse(object.isField(ANY_THING));
        assertTrue(object.optField(ANY_THING).isEmpty());
        assertTrue(object.optType(ANY_THING).isEmpty());
        assertThrows(JsonException.class, () -> object.getField(ANY_THING));
        assertThrows(JsonException.class, () -> object.getType(ANY_THING));
        assertFalse(object.nameStream().findFirst().isPresent());
    }

    @Test
    void addInteger() {
        final JsonField field;

        object = builder.add(INT_FIELD, 1).build();
        assertNotNull(object);
        assertTrue(object.isField(INT_FIELD));
        assertTrue(object.optField(INT_FIELD).isPresent());
        assertTrue(object.optType(INT_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(INT_FIELD));
        field = object.getField(INT_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(1, field.getInteger());
        assertEquals(1L, field.getLong());
    }

    @Test
    void addLong() {
        final JsonField field;

        object = builder.add(LONG_FIELD, 1000L).build();
        assertNotNull(object);
        assertTrue(object.isField(LONG_FIELD));
        assertTrue(object.optField(LONG_FIELD).isPresent());
        assertTrue(object.optType(LONG_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(LONG_FIELD));
        field = object.getField(LONG_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(1000L, field.getLong());
        assertEquals(1000, field.getInteger());
        assertEquals(1000.0, field.getDouble());
    }

    @Test
    void addDouble() {
        final JsonField field;

        object = builder.add(DOUBLE_FIELD, 0.01).build();
        assertNotNull(object);
        assertTrue(object.isField(DOUBLE_FIELD));
        assertTrue(object.optField(DOUBLE_FIELD).isPresent());
        assertTrue(object.optType(DOUBLE_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(DOUBLE_FIELD));
        field = object.getField(DOUBLE_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(0.01, field.getDouble());
        assertEquals(0, field.getInteger());
        assertEquals(0, field.getLong());
    }

    @Test
    void addBigDecimal() {
        final JsonField field;

        object = builder.add(BIG_DECIMAL_FIELD, BigDecimal.TEN).build();
        assertNotNull(object);
        assertTrue(object.isField(BIG_DECIMAL_FIELD));
        assertTrue(object.optField(BIG_DECIMAL_FIELD).isPresent());
        assertTrue(object.optType(BIG_DECIMAL_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(BIG_DECIMAL_FIELD));
        field = object.getField(BIG_DECIMAL_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(BigDecimal.TEN, field.getBigDecimal());
        assertEquals(10, field.getInteger());
        assertEquals(10.0, field.getDouble());
    }

    @Test
    void addBigInteger() {
        final JsonField field;

        object = builder.add(BIG_INTEGER_FIELD, BigInteger.TEN).build();
        assertNotNull(object);
        assertTrue(object.isField(BIG_INTEGER_FIELD));
        assertTrue(object.optField(BIG_INTEGER_FIELD).isPresent());
        assertTrue(object.optType(BIG_INTEGER_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(BIG_INTEGER_FIELD));
        field = object.getField(BIG_INTEGER_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(BigInteger.TEN, field.getBigInteger());
        assertEquals(BigDecimal.TEN, field.getBigDecimal());
        assertEquals(10, field.getInteger());
        assertEquals(10.0, field.getDouble());
    }

    @Test
    void addString() {
        final JsonField field;

        object = builder.add(STRING_FIELD, "content").build();
        assertNotNull(object);
        assertTrue(object.isField(STRING_FIELD));
        assertTrue(object.optField(STRING_FIELD).isPresent());
        assertTrue(object.optType(STRING_FIELD).isPresent());
        assertEquals(JsonType.STRING, object.getType(STRING_FIELD));
        field = object.getField(STRING_FIELD);
        assertEquals(JsonType.STRING, field.getType());
        assertEquals("content", field.getString());
        assertThrows(NumberFormatException.class, field::getInteger);
    }

    @Test
    void object() {
        final JsonField field;
        final JsonObject jsonObject;

        object = builder.object(OBJECT_FIELD).endObject().build();
        assertNotNull(object);
        assertTrue(object.isField(OBJECT_FIELD));
        assertTrue(object.optField(OBJECT_FIELD).isPresent());
        assertTrue(object.optType(OBJECT_FIELD).isPresent());
        assertEquals(JsonType.OBJECT, object.getType(OBJECT_FIELD));
        field = object.getField(OBJECT_FIELD);
        assertNotNull(field);
        assertEquals(JsonType.OBJECT, field.getType());
        jsonObject = field.getJsonObject();
        assertNotNull(jsonObject);
    }

    @Test
    void notClosedObject() {
        assertThrows(IllegalStateException.class, builder.object(OBJECT_FIELD)::build);
    }

    @Test
    void prematuringClosingObject() {
        assertThrows(IllegalStateException.class, builder::endObject);
    }

    @Test
    void chainedObjects() {
        object = builder.object(OBJECT_FIELD).add(STRING_FIELD, "content").endObject().build();
        assertNotNull(object);
        assertEquals("content", object.getField(OBJECT_FIELD).getJsonObject().getField(STRING_FIELD).getString());
    }

    @Test
    void resumedChainedObjects() {
        object = builder.object(OBJECT_FIELD).add(STRING_FIELD, "content").endObject().build();
        assertNotNull(object);
        assertEquals("content", object.getJsonObject(OBJECT_FIELD).getString(STRING_FIELD));
    }

    @Test
    void addBoolean() {
        object = builder.add("trueField", true).add("falseField", false).build();
        assertNotNull(object);
        assertTrue(object.getBoolean("trueField"));
        assertFalse(object.getBoolean("falseField"));
    }

    @Test
    void addNullNamed() {
        object = builder.addNull("nullField").build();
        assertNotNull(object);
        assertNotNull(object.getField("nullField"));
    }

    @Test
    void array() {
        final Stream<JsonField> fieldStream;
        object = builder.array("arrayField").endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream("arrayField");
        assertNotNull(fieldStream);
        assertTrue(fieldStream.findFirst().isEmpty());
    }

    @Test
    void arrayPrematurallyClosed() {
        assertThrows(IllegalStateException.class, builder.array("arrayField")::build);
    }

    void addElement() {
        fail("Not implemented");
    }

    void testAddElement() {
        fail("Not implemented");
    }

    void addNull() {
        fail("Not implemented");
    }

    void endArray() {
        fail("Not implemented");
    }

    void testObject() {
        fail("Not implemented");
    }

}
