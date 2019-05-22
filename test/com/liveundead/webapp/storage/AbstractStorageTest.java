package com.liveundead.webapp.storage;


import com.liveundead.webapp.exception.ExistStorageException;
import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public abstract class AbstractStorageTest {
    private Storage storage;

    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String FULLNAME_1 = "иван";
    private final static String FULLNAME_2 = "василий";
    private final static String FULLNAME_3 = "аркадий";

    private final static Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    private final static Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    private final static Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);


    AbstractStorageTest(Storage storage) {
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
        Resume resume = new Resume(UUID_1, FULLNAME_1);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1, FULLNAME_1));
    }

    @Test
    public void save() {
        storage.clear();
        Resume resume = new Resume(UUID_1, FULLNAME_1);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(UUID_1, FULLNAME_1));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2, FULLNAME_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1, FULLNAME_1));
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = new ArrayList<>();
        expected.add(RESUME_1);
        expected.add(RESUME_2);
        expected.add(RESUME_3);

        List<Resume> actual = storage.getAllSorted();
        actual.removeAll(Collections.singleton(null));

        Assert.assertEquals(expected, actual);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy", " ");
    }


    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        storage.save(RESUME_1);
    }
}