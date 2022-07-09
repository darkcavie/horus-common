package org.horus.json.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectImplTest {

    private JsonObjectImpl impl;

    @BeforeEach
    void setUp() {
        impl = new JsonObjectImpl();
    }

    @Test
    void tryAdd() {
        assertDoesNotThrow(() -> impl.tryAdd("fieldName", new TrueField()));
        assertThrows(IllegalStateException.class, () -> impl.tryAdd("fieldName", new FalseField()));
    }
    
}
