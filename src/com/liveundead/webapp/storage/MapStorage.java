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
    protected void updateResume(Resume r, int index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int getIndex(String uuid) {

        if (storage.containsKey(uuid)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    protected void saveResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.values().toArray(new Resume[size-1]);
        Arrays.sort(resumes);
        return resumes;
    }
}
