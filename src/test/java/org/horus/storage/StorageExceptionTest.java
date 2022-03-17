package org.horus.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageExceptionTest {

    private StorageException exception;

    @Test
    void justMessage() {
        exception = new StorageException("mock message");
        assertEquals("mock message", exception.getMessage());
    }

    @Test
    void withCause() {
        exception = new StorageException("mock message", new Exception("simple"));
        assertNotNull(exception.getCause());
    }

}
