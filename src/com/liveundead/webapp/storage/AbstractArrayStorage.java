package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.ExistStorageException;
import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index < 0) {
            saveResume(r);
            size++;
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(int index);
}