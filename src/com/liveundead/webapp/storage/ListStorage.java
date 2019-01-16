package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object index) {
        storage.set((Integer)index, r);
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size(); i++){
            if(storage.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected void saveResume(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid, Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void deleteResume(String uuid, Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[size()-1]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
