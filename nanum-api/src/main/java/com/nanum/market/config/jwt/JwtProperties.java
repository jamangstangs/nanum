package com.nanum.market.config.jwt;

public interface JwtProperties {
    String SECRET ="market_secretkey";
    int EXPIRATION_TIME =60000*100;
    String TOKEN_PREFIX ="Bearer ";
    String HEADER_STRING ="Authorization";
}