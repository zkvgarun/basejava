package com.liveundead.webapp.storage;

import com.liveundead.webapp.exception.ExistStorageException;
import com.liveundead.webapp.exception.NotExistStorageException;
import com.liveundead.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object index = getSearchKey(r.getUuid(), r.getFullName());

        if (!isExist(index)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    @Override
    public void save(Resume r) {
        Object index = getSearchKey(r.getUuid(), r.getFullName());

        if (isExist(index)) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(r, index);
    }

    @Override
    public Resume get(String uuid, String fullName) {
        Object index = getSearchKey(uuid, fullName);

        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getResume(index);
        }
    }

    @Override
    public void delete(String uuid, String fullName) {
        Object index = getSearchKey(uuid, fullName);

        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract Object getSearchKey(String uuid, String fullName);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

}
