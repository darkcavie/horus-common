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
    public JsonObjectBuilder add(String fieldName, boolean value) {
        final JsonField field;

        checkNullArrayPointer();
        field = value ? new TrueField() : new FalseField();
        pointer.tryAdd(fieldName, field);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, int value) {
        pointer.tryAdd(fieldName, new IntegerField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, long value) {
        pointer.tryAdd(fieldName, new LongField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, double value) {
        pointer.tryAdd(fieldName, new DoubleField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, BigInteger value) {
        if(value == null) {
            tryAddNull(fieldName);
        } else {
            pointer.tryAdd(fieldName, new BigIntegerField(value));
        }
        return this;
    }

    void tryAddNull(String fieldName) {
        pointer.tryAdd(fieldName, new NullField());
    }

    @Override
    public JsonObjectBuilder add(String fieldName, BigDecimal value) {
        if(value == null) {
            tryAddNull(fieldName);
        } else {
            pointer.tryAdd(fieldName, new BigDecimalField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, String value) {
        if(value == null) {
            tryAddNull(fieldName);
        } else {
            pointer.tryAdd(fieldName, new StringField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, JsonObject value) {
        return this;
    }

    @Override
    public JsonObjectBuilder array(String fieldName) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(boolean value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(int value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(long value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(double value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(BigInteger value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(BigDecimal value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(String value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(JsonObject value) {
        return this;
    }

    @Override
    public JsonObjectBuilder addNull() {
        checkArrayPointer();
        arrayPointer.add(new NullField());
        return this;
    }

    @Override
    public JsonObjectBuilder endArray() {
        return this;
    }

    @Override
    public JsonObjectBuilder object(String fieldName) {
        return this;
    }

    @Override
    public JsonObjectBuilder endObject() {
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
