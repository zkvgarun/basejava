package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    public void saveResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        fillCell(resume, (Integer) index);
        size++;
    }

    @Override
    public void deleteResume(Object index) {
        deleteCell((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void fillCell(Resume resume, int index);

    protected abstract void deleteCell(int index);
}