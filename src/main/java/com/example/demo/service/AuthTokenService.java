package com.example.demo.service;

import com.example.demo.entity.AuthInfo;
import com.example.demo.entity.Login;
import com.example.demo.entity.Role;

/**
 * Created by babu.kannar@indianic.com on 10/11/2016.
 */
public interface AuthTokenService {
    String generateToken(String origin, int userId, Role role, String password);
    AuthInfo getAuthToken(String token, boolean regenerateToken);
    void removeToken(String token);
	String generateTokenLogin(String origin, Integer id, Login login);
}
