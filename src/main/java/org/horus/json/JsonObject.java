package org.horus.json;

import java.util.Optional;
import java.util.stream.Stream;

public interface JsonObject {

    boolean isField(String fieldName);

    JsonField getField(String fieldName);

    JsonType getType(String fieldName);

    Optional<JsonField> optField(String fieldName);

    Optional<JsonType> optType(String fieldName);

    Stream<String> nameStream();

    Stream<JsonField> arrayStream(String fieldName);

    JsonObject getJsonObject(String fieldName);

    String getString(String fieldName);

    boolean getBoolean(String fieldName);

}
