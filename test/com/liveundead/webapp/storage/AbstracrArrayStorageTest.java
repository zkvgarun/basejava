package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstracrArrayStorageTest {
    private Storage storage;

    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";

    public AbstracrArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getAll().length);
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        try {
            Resume resume = new Resume(UUID_1);
            storage.update(resume);
        } catch (NotExistStorageException nee) {
            Assert.fail("NotExistStorageException thrown");
        }

        storage.update(new Resume("uuid4"));
    }

    @Test(expected = StorageException.class)
    public void save() {
        try {
            storage.clear();
            storage.save(new Resume(UUID_1));
            storage.save(new Resume(UUID_2));
            storage.save(new Resume(UUID_3));
        } catch (StorageException se) {
            Assert.fail("StorageException thrown");
        }

        storage.save(new Resume("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        try {
            storage.delete(UUID_2);
        } catch (NotExistStorageException nese) {
            Assert.fail("NotExistStorageException thrown");
        }
        storage.delete(UUID_2);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(storage.getAll(), resumes);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}