package com.example.demo.service;

import org.springframework.core.io.Resource;

import com.example.demo.entity.Role;
import com.example.demo.response.MenuResponse;

import java.util.List;

public interface ApplicationService {
    List<MenuResponse> getMainMenu(Role role);
    String getStorageAppUrl();
    String getSecretKey();
    String getAuthTokenParamName();
    String getImageUploadFolder();
    String getUserFolderName();

    String getProfileImageName();
    String getAlbumImageName();

    String getProfileSmallImageName();
    String getProfileMediumImageName();
    String getProfileLargeImageName();


    Resource getPushCertificationResource();
    String getP12FilePassword();

    String getWebAppPort();
    String getWebAppName();
    String getStorageAppName();
    String getAppName();
   /* String getReplyEmailAddress();
    String getFromEmailAddress();
    String getFromEmailName();*/
    String getAppLink();
    String getWebAppLink();
    String getStorageAppLink();
}
