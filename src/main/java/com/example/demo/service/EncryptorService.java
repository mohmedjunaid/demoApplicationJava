package com.example.demo.service;

public interface EncryptorService {
    String encryptPassword(String password);
    boolean matchPassword(String password, String encryptedPassword);
    String encryptText(String text);
    String decryptText(String encryptText);
}
