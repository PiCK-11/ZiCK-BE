package com.pick.zick.global.security.jwt;

public interface TokenBlacklist {
    void blacklist(String token, long ttlMillis);
    boolean isBlacklisted(String token);
}
