package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstracrArrayStorage {

    @Override
    protected void saveResume(Resume r) {

        int index = Math.abs(getIndex(r.getUuid())) -1; //getting index to insert
        for (int i = size-1; i >= index; i--) {
            storage[i+1] = storage[i];
        }
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int length = size - index; // number of copying elements;
        System.arraycopy(storage, index+1, storage, index, length);
        storage[size] = null;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}