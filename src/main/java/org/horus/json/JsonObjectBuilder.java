package org.horus.json;

import org.horus.json.impl.BigDecimalField;
import org.horus.json.impl.BigIntegerField;
import org.horus.json.impl.DoubleField;
import org.horus.json.impl.FalseField;
import org.horus.json.impl.IntegerField;
import org.horus.json.impl.JsonArray;
import org.horus.json.impl.JsonArrayField;
import org.horus.json.impl.JsonObjectField;
import org.horus.json.impl.JsonObjectImpl;
import org.horus.json.impl.LongField;
import org.horus.json.impl.NullField;
import org.horus.json.impl.StringField;
import org.horus.json.impl.TrueField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class JsonObjectBuilder implements JsonChain {

    private final JsonObjectImpl radix;

    private final Deque<Object> stack;

    private JsonObjectImpl pointer;

    private JsonArray arrayPointer;

    public JsonObjectBuilder() {
        radix = new JsonObjectImpl();
        stack = new ArrayDeque<>();
        pointer = radix;
    }

    public JsonObject build() {
        checkPointingObject();
        if(pointer != radix) {
            throw new IllegalStateException("Not closed object");
        }
        return radix;
    }

    void checkPointingObject() {
        if(pointer == null) {
            throw new IllegalStateException("Pointing to array");
        }
    }

    @Override
    public JsonObjectBuilder add(String fieldName, boolean value) {
        final JsonField field;

        checkPointingObject();
        field = value ? new TrueField() : new FalseField();
        pointer.tryAdd(fieldName, field);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, int value) {
        checkPointingObject();
        pointer.tryAdd(fieldName, new IntegerField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, long value) {
        checkPointingObject();
        pointer.tryAdd(fieldName, new LongField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, double value) {
        checkPointingObject();
        pointer.tryAdd(fieldName, new DoubleField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, BigInteger value) {
        checkPointingObject();
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
        checkPointingObject();
        if(value == null) {
            tryAddNull(fieldName);
        } else {
            pointer.tryAdd(fieldName, new BigDecimalField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder add(String fieldName, String value) {
        checkPointingObject();
        if(value == null) {
            tryAddNull(fieldName);
        } else {
            pointer.tryAdd(fieldName, new StringField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder addNull(String fieldName) {
        checkPointingObject();
        pointer.tryAdd(fieldName, new NullField());
        return this;
    }

    @Override
    public JsonObjectBuilder array(String fieldName) {
        checkPointingObject();
        arrayPointer = new JsonArray();
        pointer.tryAdd(fieldName, new JsonArrayField(arrayPointer));
        stack.addFirst(pointer);
        pointer = null;
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(boolean value) {
        checkPointingArray();
        arrayPointer.add(value ? new TrueField() : new FalseField());
        return this;
    }

    void checkPointingArray() {
        if(arrayPointer == null) {
            throw new IllegalStateException("Pointing to object");
        }
    }

    @Override
    public JsonObjectBuilder addElement(int value) {
        checkPointingArray();
        arrayPointer.add(new IntegerField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(long value) {
        checkPointingArray();
        arrayPointer.add(new LongField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(double value) {
        checkPointingArray();
        arrayPointer.add(new DoubleField(value));
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(BigInteger value) {
        checkPointingArray();
        if(value == null) {
            tryAddNullElement();
        } else {
            arrayPointer.add(new BigIntegerField(value));
        }
        return this;
    }

    void tryAddNullElement() {
        arrayPointer.add(new NullField());
    }

    @Override
    public JsonObjectBuilder addElement(BigDecimal value) {
        checkPointingArray();
        if(value == null) {
            tryAddNullElement();
        } else {
            arrayPointer.add(new BigDecimalField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder addElement(String value) {
        checkPointingArray();
        if(value == null) {
            tryAddNullElement();
        } else {
            arrayPointer.add(new StringField(value));
        }
        return this;
    }

    @Override
    public JsonObjectBuilder addArray() {
        final JsonArray newArray;

        checkPointingArray();
        newArray = new JsonArray();
        arrayPointer.add(new JsonArrayField(newArray));
        stack.addFirst(arrayPointer);
        arrayPointer = newArray;
        return this;
    }

    @Override
    public JsonObjectBuilder addNull() {
        checkPointingArray();
        tryAddNullElement();
        return this;
    }

    @Override
    public JsonObjectBuilder endArray() {
        checkPointingArray();
        pull();
        return this;
    }

    void pull() {
        final Object pulled;

        pulled = stack.pop();
        if(pulled instanceof JsonObjectImpl) {
            pointer = (JsonObjectImpl) pulled;
            arrayPointer = null;
            return;
        }
        if(pulled instanceof JsonArray) {
            arrayPointer = (JsonArray) pulled;
            pointer = null;
        }
    }

    @Override
    public JsonObjectBuilder object(String fieldName) {
        final JsonObjectImpl newObject;

        checkPointingObject();
        stack.addFirst(pointer);
        newObject = new JsonObjectImpl();
        pointer.tryAdd(fieldName, new JsonObjectField(newObject));
        pointer = newObject;
        return this;
    }

    @Override
    public JsonObjectBuilder endObject() {
        if(pointer == radix) {
            throw new IllegalStateException("In the radix object");
        }
        checkPointingObject();
        pull();
        return this;
    }

}
