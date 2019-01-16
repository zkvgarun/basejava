package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.ExistStorageException;
import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        clearStorage();
    }

    @Override
    public void update(Resume r) {
        Object index = getIndex(r.getUuid());

        if (!isExist(index)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    @Override
    public void save(Resume r) {
        Object index = getIndex(r.getUuid());

        if (isExist(index)) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getIndex(uuid);

        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(uuid, index);
        }
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);

        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid, index);
        }
    }

    protected abstract void clearStorage();

    protected abstract void updateResume(Resume r, Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract void saveResume(Resume r, Object index);

    protected abstract Resume getResume(String uuid, Object index);

    protected abstract void deleteResume(String uuid, Object index);

    protected abstract boolean isExist(Object index);

}
