package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.entity.AuthInfo;
import com.example.demo.service.AuthTokenService;

/**
 * Created by Kishan Maheshwary on 2/20/2017.
 */
public abstract class BaseController {

    protected String getAuthToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getCredentials().toString();
    }

    protected AuthInfo getAuthInfo(AuthTokenService authTokenService) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return authTokenService.getAuthToken(auth.getCredentials().toString(), false);
    }


}
