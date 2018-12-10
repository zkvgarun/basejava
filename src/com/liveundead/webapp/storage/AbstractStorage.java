package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.ExistStorageException;
import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.exception.StorageException;
import com.liveundead.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        clearStorage();
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    @Override
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

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(uuid, index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid, index);
            size--;
        }
    }

    protected abstract void clearStorage();

    protected abstract void updateResume(Resume r, int index);

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void deleteResume(String uuid, int index);

}
