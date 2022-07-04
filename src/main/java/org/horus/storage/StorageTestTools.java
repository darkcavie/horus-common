package org.horus.storage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class StorageTestTools {

    StorageTestTools() throws IllegalAccessException {
        throw new IllegalAccessException("static methods class");
    }

    public static <K,V> Storage<K,V> voidStorage() {
        return new Storage<>(){

            @Override
            public void upsert(K key, V transfer) {
            }

            @Override
            public void getByKey(K key, Supplier<OutputStream> outputStream) {
            }

            @Override
            public void getByKey(K key, Consumer<V> consumer) {
            }

            @Override
            public void deleteByKey(K key) {
            }
        };
    }

    public static <K,V> Storage<K,V> failStorage() {
        return new Storage<>() {

            @Override
            public void upsert(K key, V transfer) throws StorageException {
                throw new StorageException("Failing upsert");
            }

            @Override
            public void getByKey(K key, Supplier<OutputStream> outputStream) throws StorageException {
                throw new StorageException("Failing get by key to output stream");
            }

            @Override
            public void getByKey(K key, Consumer<V> consumer) throws StorageException {
                throw new StorageException("Failing get by key to consumer");
            }

            @Override
            public void deleteByKey(K key) throws StorageException {
                throw new StorageException("Failing delete");
            }
        };
    }

    public static <K,V> Storage<K,V> memoryStorage() {
        return new Storage<>() {

            private final Map<K, V> map = new ConcurrentHashMap<>();

            @Override
            public void upsert(K key, V transfer) {
                map.put(key, transfer);
            }

            @Override
            public void getByKey(K key, Supplier<OutputStream> outputStream) throws StorageException {
                final V value;

                requireNonNull(outputStream, "The output supplier must exist");
                value = map.get(key);
                if(value != null) {
                    try {
                        outputStream.get().write(value.toString().getBytes());
                    } catch(IOException ioEx) {
                        throw new StorageException("Error writing in stream", ioEx);
                    }
                }
            }

            @Override
            public void getByKey(K key, Consumer<V> consumer) {
                Optional.ofNullable(map.get(key))
                        .ifPresent(consumer);
            }

            @Override
            public void deleteByKey(K key) {
                map.remove(key);
            }
        };
    }

}
