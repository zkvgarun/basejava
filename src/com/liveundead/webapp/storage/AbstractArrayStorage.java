package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    public void saveResume(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        fillCell(r, (Integer) searchKey);
        size++;
    }

    @Override
    public void deleteResume(Object searchKey) {
        deleteCell((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void fillCell(Resume r, int index);

    protected abstract void deleteCell(int index);
}