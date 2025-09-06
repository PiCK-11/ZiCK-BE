package com.pick.zick.global.security.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(name = "app.jwt.blacklist", havingValue = "memory", matchIfMissing = true)
public class InMemoryTokenBlacklist implements TokenBlacklist {
    private final Map<String, Long> store = new ConcurrentHashMap<>();

    @Override
    public void blacklist(String token, long ttlMillis) {
        long expireAt = System.currentTimeMillis() + Math.max(ttlMillis, 0L);
        store.put(token, expireAt);
    }

    @Override
    public boolean isBlacklisted(String token) {
        Long exp = store.get(token);
        if (exp == null) return false;
        if (exp < System.currentTimeMillis()) {
            store.remove(token);
            return false;
        }
        return true;
    }
}