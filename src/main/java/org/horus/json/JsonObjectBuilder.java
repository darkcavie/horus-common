package org.horus.json;

import org.horus.json.impl.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonObjectBuilder implements JsonChain {

    private final JsonObjectImpl radix;

    private final JsonObjectImpl pointer;

    private JsonArray arrayPointer;

    public JsonObjectBuilder() {
        radix = new JsonObjectImpl();
        pointer = radix;
    }

    public JsonObject build() {
        checkNullArrayPointer();
        if(pointer != radix) {
            throw new IllegalStateException("Not closed object");
        }
        return radix;
    }

    @Override
    public JsonChain add(String fieldName, boolean value) {
        final JsonField field;

        checkNullArrayPointer();
        field = value ? new TrueField() : new FalseField();
        pointer.tryAdd(fieldName, field);
        return this;
    }

    @Override
    public JsonChain add(String fieldName, int value) {
        pointer.tryAdd(fieldName, new IntegerField(value));
        return this;
    }

    @Override
    public JsonChain add(String fieldName, long value) {
        return this;
    }

    @Override
    public JsonChain add(String fieldName, double value) {
        return this;
    }

    @Override
    public JsonChain add(String fieldName, BigInteger value) {
        return this;
    }

    @Override
    public JsonChain add(String fieldName, BigDecimal value) {
        return this;
    }

    @Override
    public JsonChain add(String fieldName, String value) {
        return this;
    }

    @Override
    public JsonChain add(String fieldName, JsonObject value) {
        return this;
    }

    @Override
    public JsonChain array(String fieldName) {
        return this;
    }

    @Override
    public JsonChain addElement(boolean value) {
        return this;
    }

    @Override
    public JsonChain addElement(int value) {
        return this;
    }

    @Override
    public JsonChain addElement(long value) {
        return this;
    }

    @Override
    public JsonChain addElement(double value) {
        return this;
    }

    @Override
    public JsonChain addElement(BigInteger value) {
        return this;
    }

    @Override
    public JsonChain addElement(BigDecimal value) {
        return this;
    }

    @Override
    public JsonChain addElement(String value) {
        return this;
    }

    @Override
    public JsonChain addElement(JsonObject value) {
        return this;
    }

    @Override
    public JsonChain addNull() {
        checkArrayPointer();
        arrayPointer.add(new NullField());
        return this;
    }

    @Override
    public JsonChain endArray() {
        return this;
    }

    @Override
    public JsonChain object(String fieldName) {
        return this;
    }

    @Override
    public JsonChain endObject() {
        return this;
    }

    private void checkNullArrayPointer() {
        if(arrayPointer != null) {
            throw new IllegalStateException("Not closed array");
        }
    }

    private void checkArrayPointer() {
        if(arrayPointer == null) {
            throw new IllegalStateException("Not opened array");
        }
    }


}
