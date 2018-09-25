package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstracrArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для хранения резюме");
        } else if (index > 0) {
            System.out.println("Resume уже есть");
        } else {
            int i;
            for (i = size - 1; (i >= 0 && storage[i].compareTo(r) > 0); i--) {
                storage[i + 1] = storage[i];
            }
            storage[i + 1] = r;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}