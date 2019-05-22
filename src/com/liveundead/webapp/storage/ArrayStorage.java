package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillCell(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteCell(int index) {
        storage[index] = storage[size - 1];
    }

    protected Object getSearchKey(String uuid, String fullName) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}