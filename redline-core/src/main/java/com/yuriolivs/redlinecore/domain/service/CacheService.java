package com.yuriolivs.redlinecore.domain.service;

public interface CacheService {
    String put(String key, String value, Integer ttl);
    String get(String key);
    void delete(String key);
}
