package org.horus.storage;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class StorageTestToolsTest {

     @Test
     void noConstructor() {
         assertThrows(IllegalAccessException.class, StorageTestTools::new);
     }

    @Test
    void voidStorage() {
         final Storage<Void, Void> voidStorage;

         voidStorage = StorageTestTools.voidStorage();
         assertNotNull(voidStorage);
         assertDoesNotThrow(() -> voidStorage.getByKey(null, v -> fail("It must not return")));
         assertDoesNotThrow(() -> voidStorage.getByKey(null, () -> {
             fail("It must not write");
             return null;
         }));
         assertDoesNotThrow(() -> voidStorage.upsert(null, null));
         assertDoesNotThrow(() -> voidStorage.deleteByKey(null));
    }

    @Test
    void failStorage() {
        final Storage<Void, Void> voidStorage;

        voidStorage = StorageTestTools.failStorage();
        assertNotNull(voidStorage);
        assertThrows(StorageException.class, () -> voidStorage.getByKey(null, v -> fail("It must not return")));
        assertThrows(StorageException.class, () -> voidStorage.getByKey(null, () -> {
            fail("It must not write");
            return null;
        }));
        assertThrows(StorageException.class, () -> voidStorage.upsert(null, null));
        assertThrows(StorageException.class, () -> voidStorage.deleteByKey(null));
    }

    @Test
    void memoryStorageUpsert() {
         assertDoesNotThrow(() -> StorageTestTools.<Integer, String>memoryStorage()
                 .upsert(1, "Uno"));
    }

    @Test
    void memoryStorageDelete() {
         assertDoesNotThrow(() -> StorageTestTools.<Integer, String>memoryStorage()
                 .deleteByKey(1));
    }

    @Test
    void memoryStorageGetByKeyConsumer() throws StorageException {
         final Storage<Integer, String> storage;

         storage = StorageTestTools.memoryStorage();
         storage.upsert(2, "Two");
         storage.getByKey(2, v -> assertEquals("Two", v));
    }

    @Test
    void memoryStorageGetByKeySupplier() throws StorageException {
        final Storage<Integer, String> storage;
        final ByteArrayOutputStream outputStream;

        storage = StorageTestTools.memoryStorage();
        storage.upsert(3, "Three");
        outputStream = new ByteArrayOutputStream();
        storage.getByKey(3, () -> outputStream);
        assertNotEquals(0, outputStream.size());
    }

    @Test
    void memoryStorageGetByKeyNotFoundSupplier() throws StorageException {
        final Storage<Integer, String> storage;

        storage = StorageTestTools.memoryStorage();
        storage.getByKey(3, () -> fail("Not Found, it not must call"));
    }

    @Test
    void memoryStorageGetByKeyIOException() throws StorageException {
        final Storage<Integer, String> storage;

        storage = StorageTestTools.memoryStorage();
        storage.upsert(1, "One");
        assertThrows(StorageException.class, () -> storage.getByKey(1, () -> new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException("Mock input output exception");
            }
        }));
    }

}
