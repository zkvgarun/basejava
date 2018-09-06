package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static int count;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, null);
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
        boolean isPresent = Arrays.asList(storage).contains(r);

        if(isPresent){
            for(int i = 0; i < count; i++){
                if(r.getUuid().equals(storage[i].getUuid())){
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("Resume не найден");
        }
    }

    public Resume get(String uuid) {
        boolean isPresent = false;

        for(int i = 0; i < count; i++){
            if (uuid.equals(storage[i].getUuid())){
                isPresent = true;
                break;
            }
        }

        if(isPresent) {
            for (int i = 0; i < count; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("Resume не найден");
        }
        return new Resume();
    }

    public void delete(String uuid) {
        boolean isPresent = false;

        //Поиск резюме
        for(int i = 0; i < count; i++){
            if(uuid.equals(storage[i].getUuid())){
                isPresent = true;
                break;
            }
        }

        // Проверка
        if(isPresent) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    --count;
                    System.arraycopy(storage, count, storage, i, 1);
                    return;
                }
            }
        } else {
            System.out.println("Resume не найден");
        }
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