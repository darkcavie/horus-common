package org.horus.storage;

import java.util.function.Consumer;

@FunctionalInterface
public interface Searchable<Q, T> {

    void searchByQuery(Q query, Consumer<T> consumer) throws StorageException;

}
