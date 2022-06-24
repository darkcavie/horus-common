package org.horus.json;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface JsonChain {

    JsonChain add(String fieldName, boolean value);

    JsonChain add(String fieldName, int value);

    JsonChain add(String fieldName, long value);

    JsonChain add(String fieldName, double value);
        
    JsonChain add(String fieldName, BigInteger value);

    JsonChain add(String fieldName, BigDecimal value);

    JsonChain add(String fieldName, String value);

    JsonChain add(String fieldName, JsonObject value);

    JsonChain array(String fieldName);

    JsonChain addElement(boolean value);

    JsonChain addElement(int value);

    JsonChain addElement(long value);

    JsonChain addElement(double value);

    JsonChain addElement(BigInteger value);

    JsonChain addElement(BigDecimal value);

    JsonChain addElement(String value);

    JsonChain addElement(JsonObject value);

    JsonChain addNull();

    JsonChain endArray();

    JsonChain object(String fieldName);

    JsonChain endObject();

}
