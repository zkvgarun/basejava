package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

public class ArrayStorage extends AbstracrArrayStorage {

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (super.size == storage.length) {
            System.out.println("Нет места для хранения резюме");
        } else if (index < 0) {
            storage[super.size] = r;
            super.size++;
        } else {
            System.out.println("Resume уже есть");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < super.size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}