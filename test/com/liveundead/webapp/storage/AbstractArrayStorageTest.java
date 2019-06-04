package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        try {
            super.clear();
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                super.storage.save(new Resume(""));
            }
        } catch (StorageException se) {
            Assert.fail("StorageException thrown");
        }

        super.storage.save(new Resume(""));
    }
}
