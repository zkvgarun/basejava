package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();


    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Object getIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void saveResume(Resume r, Object index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String uuid, Object index) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid, Object index) {
        storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.values().toArray(new Resume[size()]);
        Arrays.sort(resumes);
        return resumes;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public int size() {
        return 0;
    }
}
