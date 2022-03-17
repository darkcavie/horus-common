package org.horus.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundStorageExceptionTest {

    private NotFoundStorageException exception;

    @Test
    void justMessage() {
        exception = new NotFoundStorageException("mock message");
        assertEquals("mock message", exception.getMessage());
    }

    @Test
    void withCause() {
        exception = new NotFoundStorageException("mock message", new Exception("simple"));
        assertNotNull(exception.getCause());
    }

}