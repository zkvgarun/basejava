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
    private final static String UUID_4 = "uuid4";

    private final static Resume RESUME_1 = new Resume(UUID_1);
    private final static Resume RESUME_2 = new Resume(UUID_2);
    private final static Resume RESUME_3 = new Resume(UUID_3);
    private final static Resume RESUME_4 = new Resume(UUID_4);

    AbstracrArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
    }

    @Test
    public void save() {
        try {
            storage.clear();
            storage.save(RESUME_1);
            storage.save(RESUME_2);
            storage.save(RESUME_3);
        } catch (StorageException se) {
            Assert.fail("StorageException thrown");
        }
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(storage.getAll(), resumes);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        storage.save(RESUME_4);
    }
}