package com.dfoucs.gateway.auth;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public interface Auth {
    String getToken(Map<String,Object> map) throws Exception;
    Map<String, Claim> verify(String token) throws Exception;
}
