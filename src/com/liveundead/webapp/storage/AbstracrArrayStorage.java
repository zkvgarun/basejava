package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstracrArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume не найден");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (size == STORAGE_LIMIT) {
            System.out.println("Нет места для хранения резюме");
        } else if (index < 0) {
            saveResume(r);
            size++;
        } else {
            System.out.println("Resume уже есть");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Resume не найден");
        } else {
            size--;
            deleteResume(index);
        }
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(int index);
}