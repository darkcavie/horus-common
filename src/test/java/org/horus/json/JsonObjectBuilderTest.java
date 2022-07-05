package org.horus.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectBuilderTest {

    private static final String ANY_THING = "anyThing";

    private static final String INT_FIELD = "intField";

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

        builder.add(INT_FIELD, 1);
        object = builder.build();
        assertNotNull(object);
        assertTrue(object.isField(INT_FIELD));
        assertTrue(object.optField(INT_FIELD).isPresent());
        assertTrue(object.optType(INT_FIELD).isPresent());
        assertEquals(JsonType.NUMBER, object.getType(INT_FIELD));
        field = object.getField(INT_FIELD);
        assertEquals(JsonType.NUMBER, field.getType());
        assertEquals(1, field.getInteger());
    }

    @Test
    void testAdd() {
        fail("Not implemented");
    }


    @Test
    void array() {
        fail("Not implemented");
    }

    @Test
    void addElement() {
        fail("Not implemented");
    }

    @Test
    void testAddElement() {
        fail("Not implemented");
    }

    @Test
    void addNull() {
        fail("Not implemented");
    }

    @Test
    void endArray() {
        fail("Not implemented");
    }

    @Test
    void testObject() {
        fail("Not implemented");
    }

}
