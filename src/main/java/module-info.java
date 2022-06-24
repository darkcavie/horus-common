module horus.common {
    exports org.horus.storage;
    exports org.horus.json;
    exports org.horus.json.impl;
    exports org.horus.rejection;
    exports org.horus.entitybuilder;
    requires java.base;
    requires java.sql;
    requires org.slf4j;
}