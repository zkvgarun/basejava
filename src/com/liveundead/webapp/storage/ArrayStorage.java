package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int count = 0;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume r) {
        boolean exist = Arrays.asList(storage).contains(r);
        if(exist){
            System.out.println("Resume уже есть");
        } else if (count == storage.length){
            System.out.println("Нет места для хранения резюме");
        } else {
            storage[count] = r;
            count++;
        }
    }


    public void update(Resume r){
        int index = searchResume(r.getUuid());

        if(index >= 0){
            storage[index] = r;
        } else {
            System.out.println("Resume не найден");
        }
    }

    public Resume get(String uuid) {
        int index = searchResume(uuid);

        if(index >= 0){
            return storage[index];
        } else {
            System.out.println("Resume не найден");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = searchResume(uuid);

        if(index >= 0) {
            --count;
            System.arraycopy(storage, count, storage, index, 1);
        } else {
            System.out.println("Resume не найден");
        }
    }

    // Ищет резюме и возвращает индекс
    private int searchResume(String uuid){
        for(int i = 0; i < count; i++){
            if(uuid.equals(storage[i].getUuid())){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }

    public int size() {
        return count;
    }
}