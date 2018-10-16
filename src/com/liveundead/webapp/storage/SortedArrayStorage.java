package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstracrArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        int index = Math.abs(getIndex(r.getUuid())) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int length = size - index - 1;
        System.arraycopy(storage, index + 1, storage, index, length);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}