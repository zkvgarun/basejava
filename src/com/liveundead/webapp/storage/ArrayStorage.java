package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillCell(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteCell(int index) {
        storage[index] = storage[size - 1];
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected List<Resume> getSortedResumes() {
        Arrays.sort(storage);
        return Arrays.asList(new Resume[size]);
    }
}