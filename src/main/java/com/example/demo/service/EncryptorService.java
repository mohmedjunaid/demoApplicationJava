package com.example.demo.service;

/**
 * Created by babu.kannar@indianic.com on 10/11/2016.
 */
public interface EncryptorService {
    String encryptPassword(String password);
    boolean matchPassword(String password, String encryptedPassword);
    String encryptText(String text);
    String decryptText(String encryptText);
}
