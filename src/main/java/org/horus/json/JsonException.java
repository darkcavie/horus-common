package org.horus.json;

public class JsonException extends RuntimeException {

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable exception) {
        super(message, exception);
    }

}
