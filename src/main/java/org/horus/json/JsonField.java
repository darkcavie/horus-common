package org.horus.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

public interface JsonField {
    
    boolean isNull();

    JsonType getType();

    boolean isFieldOfType(JsonType type);

    boolean getBoolean();

    int getInteger();

    long getLong();

    double getDouble();

    BigInteger getBigInteger();

    BigDecimal getBigDecimal();

    String getString();

    JsonObject getJsonObject();

    Optional<Boolean> optBoolean();

    Optional<Integer> optInteger();

    Optional<Long> optLong();

    Optional<Double> optDouble();

    Optional<BigInteger> optBigInteger();

    Optional<BigDecimal> optBigDecimal();

    Optional<String> optString();

    Optional<JsonObject> optJson();

    Stream<JsonField> arrayFieldStream();

}
