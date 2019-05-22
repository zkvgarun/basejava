package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid, String fullName);

    void delete(String uuid, String fullName);

    List<Resume> getAllSorted();

    int size();
}
