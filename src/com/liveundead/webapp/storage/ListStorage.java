package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++){
            if(storage.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
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
