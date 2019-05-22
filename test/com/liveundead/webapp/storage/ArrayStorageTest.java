package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractStorageTest {
    private Storage storage = new ArrayStorage();

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        try {
            storage.clear();
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException se) {
            Assert.fail("StorageException thrown");
        }

        storage.save(new Resume());
    }
}