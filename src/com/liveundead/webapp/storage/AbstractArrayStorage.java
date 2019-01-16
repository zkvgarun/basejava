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
    protected void updateResume(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    public void saveResume(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        fillCell(r, (Integer) index);
        size++;
    }

    @Override
    public void deleteResume(String uuid, Object index) {
        deleteCell((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(String uuid, Object index) {
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

    protected abstract void fillCell(Resume r, int index);

    protected abstract void deleteCell(int index);
}