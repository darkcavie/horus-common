package org.horus.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTypeTest {

    @Test
    void values() {
        assertNotEquals(0, JsonType.values().length);
    }

}