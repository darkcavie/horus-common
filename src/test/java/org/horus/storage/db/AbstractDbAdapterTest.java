package org.horus.storage.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDbAdapterTest {

    private MockDbAdapter adapter;

    @Test
    void resultSetMandatoryInConstructor() {
         assertThrows(NullPointerException.class, () -> new MockDbAdapter(null));
    }

    private static class MockDbAdapter extends AbstractDbAdapter {

        /**
         * Constructor wrapping Result set
         *
         * @param rs wrapped result set
         */
        public MockDbAdapter(ResultSet rs) {
            super(rs);
        }

    }
}