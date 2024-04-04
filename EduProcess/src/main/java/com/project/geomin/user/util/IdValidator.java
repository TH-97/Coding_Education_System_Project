package com.project.geomin.user.util;

import java.util.HashSet;
import java.util.Set;
//Double-Check Locking 패턴
public class IdValidator {
    private static volatile IdValidator instance;
    private Set<String> idSet;

    private IdValidator() {
        idSet = new HashSet<>();
    }

    public static IdValidator getInstance() {
        if (instance == null) {
            synchronized (IdValidator.class) {
                if (instance == null) {
                    instance = new IdValidator();
                }
            }
        }
        return instance;
    }

    public boolean isIdUnique(String id) {
        // 중복 아이디 검사
        synchronized (idSet) {
            return !idSet.contains(id);
        }
    }

    public void addId(String id) {
        // 아이디 추가
        synchronized (idSet) {
            idSet.add(id);
        }
    }

    public void removeId(String id) {
        // 아이디 제거
        synchronized (idSet) {
            idSet.remove(id);
        }
    }
    public Set<String> getIdSet() {
        return idSet;
    }
}