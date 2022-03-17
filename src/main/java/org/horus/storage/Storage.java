package org.horus.storage;

import java.io.OutputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Storage based in Transfer objects
 * @param <K> Key transfer type
 * @param <T> Transfer type to storage
 */
public interface Storage<K, T> {

    /**
     * Update or insert a transfer into storage
     * @param transfer The transfer to be updated or inserted
     * @throws StorageException in case of storage exception
     */
    void upsert(K key, T transfer) throws StorageException;

    /**
     * Get a transfer from key
     * @param key The transfer key
     * @param outputStream and output stream supplier
     * @throws StorageException in case of storage exception
     */
    void getByKey(K key, Supplier<OutputStream> outputStream) throws StorageException;

    /**
     * Get a transfer from key
     * @param key The transfer key
     * @param consumer Transfer consumer
     * @throws StorageException in case of storage exception
     */
    void getByKey(K key, Consumer<T> consumer) throws StorageException;

    /**
     * Delete a transfer in the storage from key
     * @param key The transfer key
     * @throws StorageException in case of storage exception
     */
    void deleteByKey(K key) throws StorageException;

}
