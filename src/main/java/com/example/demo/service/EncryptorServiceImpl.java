package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by babu.kannar@indianic.com on 10/11/2016.
 */
@Component
public class EncryptorServiceImpl implements EncryptorService {

    private PasswordEncoder passwordEncoder;
    private String secretKey = "ezeon8547";
    private TextEncryptor encryptor;

    @Autowired
    public EncryptorServiceImpl(ApplicationService appSettings) {
        passwordEncoder = new StandardPasswordEncoder(appSettings.getSecretKey());
        this.secretKey = appSettings.getSecretKey();
        CharSequence cs = "b890b7eb0763e9b2";
        encryptor = Encryptors.text(appSettings.getSecretKey(), cs);
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matchPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }

    @Override
    public String encryptText(String text) {
        return encryptor.encrypt(text);
    }

    @Override
    public String decryptText(String encryptText) {
        return encryptor.decrypt(encryptText);
    }

}
