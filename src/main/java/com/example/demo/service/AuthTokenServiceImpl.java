package com.example.demo.service;

import org.apache.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.AuthInfo;
import com.example.demo.entity.Login;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;
import com.example.demo.util.DateTimeUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by babu.kannar@indianic.com on 10/11/2016.
 */
@Component
public class AuthTokenServiceImpl implements AuthTokenService  {

    private final Logger log = Logger.getLogger(this.getClass());

    private CacheManager cacheManager;
    private Cache<String, AuthInfo> tokenCache;

    private EncryptorService encryptorService;

    private LoginRepository loginRepository;

    @Autowired
    public AuthTokenServiceImpl(EncryptorService encryptorService, LoginRepository loginRepository) {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, AuthInfo.class, ResourcePoolsBuilder.heap(100))
                                .withExpiry(Expirations.timeToIdleExpiration(Duration.of(60*15, TimeUnit.SECONDS))))
                .build();
        cacheManager.init();
        tokenCache = cacheManager.getCache("preConfigured", String.class, AuthInfo.class);
        this.encryptorService = encryptorService;
        this.loginRepository = loginRepository;
    }

    @Override
    public String generateToken(String origin, int userId, Role role, String password) {
        String token = null;
        AuthInfo authInfo = new AuthInfo(origin, userId, role);
        token = authInfo.toString();
        token = encryptorService.encryptText(token);
        tokenCache.put(token, authInfo);

        Login login = new Login();
        login.setToken(token);
        User user=new User();
        user.setId(userId);
        login.setUserId(user);
        login.setRole(role);
        login.setPassword(password);
        login.setLastLogin(new Date());
        login.setActive(false);
        loginRepository.save(login);

        return token;
    }
    
    @Override
	public String generateTokenLogin(String origin, Integer userId, Login login) {
    	 String token = null;
         AuthInfo authInfo = new AuthInfo(origin, userId, login.getRole());
         token = authInfo.toString();
         token = encryptorService.encryptText(token);
         tokenCache.put(token, authInfo);

         login.setToken(token);
         login.setLastLogin(new Date());
         loginRepository.save(login);

         return token;
	}

    public void removeToken(String token) {
        AuthInfo authInfo = tokenCache.get(token);
        if(authInfo != null) {
            Login login = loginRepository.findByUserIdAndToken(authInfo.getUserId(), token);
            if(login != null) {
                loginRepository.delete(login);
            }
        }
        tokenCache.remove(token);
    }

    @Override
    public AuthInfo getAuthToken(String token, boolean regenerateToken) {
        if(token == null) {
            return null;
        }
        AuthInfo authInfo = tokenCache.get(token);
        if(authInfo != null) {
            authInfo.setToken(token);
        }
        if(authInfo == null && regenerateToken) {
            String decryptToken = encryptorService.decryptText(token);
            AuthInfo authInfo2 = AuthInfo.parse(decryptToken);
            authInfo2.setToken(token);
            if(authInfo2 != null) {
                Login login = loginRepository.findByUserIdAndToken(authInfo2.getUserId(), token);
                if(login != null) {
                    tokenCache.put(token, authInfo2);
                } else {
                    authInfo2 = null;
                }
            }
            return authInfo2;
        }
        return authInfo;
    }

}
