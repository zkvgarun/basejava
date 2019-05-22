package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractStorageTest {
    private Storage storage;
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
        storage = new SortedArrayStorage();
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