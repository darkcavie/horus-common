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

    public static final String CONTENT_VALUE = "content";

    public static final String TRUE_FIELD = "trueField";

    public static final String FALSE_FIELD = "falseField";

    public static final String NULL_FIELD = "nullField";

    public static final String ARRAY_FIELD = "arrayField";

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
        object = builder.object(OBJECT_FIELD).add(STRING_FIELD, CONTENT_VALUE).endObject().build();
        assertNotNull(object);
        assertEquals(CONTENT_VALUE, object.getField(OBJECT_FIELD).getJsonObject().getField(STRING_FIELD).getString());
    }

    @Test
    void resumedChainedObjects() {
        object = builder.object(OBJECT_FIELD).add(STRING_FIELD, CONTENT_VALUE).endObject().build();
        assertNotNull(object);
        assertEquals(CONTENT_VALUE, object.getJsonObject(OBJECT_FIELD).getString(STRING_FIELD));
    }

    @Test
    void addBoolean() {
        object = builder.add(TRUE_FIELD, true).add(FALSE_FIELD, false).build();
        assertNotNull(object);
        assertTrue(object.getBoolean(TRUE_FIELD));
        assertFalse(object.getBoolean(FALSE_FIELD));
    }

    @Test
    void addNullNamed() {
        object = builder.addNull(NULL_FIELD).build();
        assertNotNull(object);
        assertNotNull(object.getField(NULL_FIELD));
    }

    @Test
    void array() {
        final Stream<JsonField> fieldStream;
        object = builder.array(ARRAY_FIELD).endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        assertTrue(fieldStream.findFirst().isEmpty());
    }

    @Test
    void arrayPrematurallyClosed() {
        assertThrows(IllegalStateException.class, builder.array(ARRAY_FIELD)::build);
    }

    @Test
    void addElementString() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD).addElement(CONTENT_VALUE).endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getString)
                .ifPresentOrElse(x -> assertEquals(CONTENT_VALUE, x), () -> fail("Must value content"));
    }

    @Test
    void addElementInteger() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD).addElement(1).endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getInteger)
                .ifPresentOrElse(i -> assertEquals(1, i), () -> fail("Must value 1"));
    }

    @Test
    void addElementLong() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD).addElement(1L).endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getLong)
                .ifPresentOrElse(i -> assertEquals(1L, i), () -> fail("Must value 1 long"));
    }

    @Test
    void addElementDouble() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD)
                .addElement(1.0)
                .endArray()
                .build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getDouble)
                .ifPresentOrElse(i -> assertEquals(1.0, i), () -> fail("Must value 1.0 long"));
    }

    @Test
    void addElementBigDecimal() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD)
                .addElement(BigDecimal.ONE)
                .endArray()
                .build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getBigDecimal)
                .ifPresentOrElse(b -> assertEquals(BigDecimal.ONE, b), () -> fail("Must value 1 big decimal"));
    }

    @Test
    void addElementBigInteger() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD)
                .addElement(BigInteger.TEN)
                .endArray()
                .build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst()
                .map(JsonField::getBigInteger)
                .ifPresentOrElse(b -> assertEquals(BigInteger.TEN, b), () -> fail("Must value 1 big integer"));
    }

    @Test
    void addNull() {
        final Stream<JsonField> fieldStream;

        object = builder.array(ARRAY_FIELD).addNull().endArray().build();
        assertNotNull(object);
        fieldStream = object.arrayStream(ARRAY_FIELD);
        assertNotNull(fieldStream);
        fieldStream.findFirst().ifPresentOrElse(n -> assertTrue(n.isNull()), () -> fail("Must be a null"));
    }

    @Test
    void endArray() {
        assertThrows(IllegalStateException.class, builder::endArray);
    }

    @Test
    void arrayPostArray() {
        object = builder.array(ARRAY_FIELD)
                .addArray()
                .endArray()
                .endArray()
                .build();
        assertNotNull(object);
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(a -> assertTrue(a.arrayFieldStream()
                        .findFirst()
                        .isEmpty()), () -> fail("One array must exist"));
    }

    @Test
    void addNullString() {
        final String nullValue = null;
        object = builder.add(STRING_FIELD, nullValue).build();
        assertTrue(object.getField(STRING_FIELD).isNull());
    }

    @Test
    void addNullBigInteger() {
        final BigInteger nullValue = null;
        object = builder.add(BIG_INTEGER_FIELD, nullValue).build();
        assertTrue(object.getField(BIG_INTEGER_FIELD).isNull());
    }

    @Test
    void addNullBigDecimal() {
        final BigDecimal nullValue = null;
        object = builder.add(BIG_DECIMAL_FIELD, nullValue).build();
        assertTrue(object.getField(BIG_DECIMAL_FIELD).isNull());
    }

    @Test
    void addElementTrue() {
        object = builder.array(ARRAY_FIELD).addElement(true).endArray().build();
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(f -> assertTrue(f.getBoolean()), () -> fail("Must be true"));
    }

    @Test
    void addElementFalse() {
        object = builder.array(ARRAY_FIELD).addElement(false).endArray().build();
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(f -> assertFalse(f.getBoolean()), () -> fail("Must be false"));
    }

    @Test
    void addElementStringNull() {
        object = builder.array(ARRAY_FIELD).addElement((String) null).endArray().build();
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(f -> assertTrue(f.isNull()), () -> fail("Must be Null"));
    }

    @Test
    void addElementBigIntegerNull() {
        object = builder.array(ARRAY_FIELD).addElement((BigInteger) null).endArray().build();
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(f -> assertTrue(f.isNull()), () -> fail("Must be Null"));
    }

    @Test
    void addElementBigDecimalNull() {
        object = builder.array(ARRAY_FIELD).addElement((BigDecimal) null).endArray().build();
        object.arrayStream(ARRAY_FIELD)
                .findFirst()
                .ifPresentOrElse(f -> assertTrue(f.isNull()), () -> fail("Must be Null"));
    }

}
