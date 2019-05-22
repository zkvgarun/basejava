package com.liveundead.webapp.storage;

import com.liveundead.webapp.model.Resume;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        String hex = md5Custom(r.getUuid() + r.getFullName());
        storage.put(hex, r);
    }

    @Override
    protected Object getSearchKey(String uuid, String fullName) {
        String hex = md5Custom(uuid + fullName);
        return storage.get(hex);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        String hex = md5Custom(r.getUuid() + r.getFullName());
        storage.put(hex, r);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        String hex = md5Custom(((Resume) searchKey).getUuid() + ((Resume) searchKey).getFullName());
        storage.remove(hex);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        String hex = md5Custom(((Resume) searchKey).getUuid() + ((Resume) searchKey).getFullName());
        return storage.get(hex);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        Collections.sort(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    private String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}