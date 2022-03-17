package org.horus.entitybuilder;

import static java.util.Objects.requireNonNull;

class MockEntity {

    private String code;

    void setCode(String code) {
        final int length;

        requireNonNull(code);
        length = code.length();
        if(length != 3) {
            throw new IllegalArgumentException("Code must have length 3 characters");
        }
        this.code = code;
    }

    String getCode() {
        return code;
    }

}
