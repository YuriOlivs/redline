package com.yuriolivs.redlinecore.domain.service;

public interface ICacheService {
    String put(String key, String value, Integer ttl);
    String get(String key);
    void delete(String key);
}
