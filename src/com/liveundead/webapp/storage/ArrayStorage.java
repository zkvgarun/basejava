package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstracrArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private int count = 0;
    private Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (count == storage.length) {
            System.out.println("Нет места для хранения резюме");
        } else if (index < 0) {
            storage[count] = r;
            count++;
        } else {
            System.out.println("Resume уже есть");
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume не найден");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume не найден");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            count--;
            storage[index] = storage[count];
            storage[count] = null;
        } else {
            System.out.println("Resume не найден");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }

    public int size() {
        return count;
    }
}