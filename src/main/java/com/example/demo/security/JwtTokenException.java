package com.example.demo.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by babu.kannar@indianic.com on 10/12/2016.
 */
public class JwtTokenException extends AuthenticationException {
    public JwtTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenException(String msg) {
        super(msg);
    }
}
