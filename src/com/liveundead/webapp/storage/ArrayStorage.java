package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

public class ArrayStorage extends AbstracrArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        storage[size] = r;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[size];
        storage[size] = null;

    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}