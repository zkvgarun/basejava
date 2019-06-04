package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.replace(resume.getUuid(),(Resume) searchKey, resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.getOrDefault(uuid, null);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public List<Resume> getSortedResumes() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        Collections.sort(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }
}