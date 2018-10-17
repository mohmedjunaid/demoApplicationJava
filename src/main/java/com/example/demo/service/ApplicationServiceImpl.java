package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Role;
import com.example.demo.response.MenuResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by babu.kannar@indianic.com on 10/6/2016.
 */
@Component
public class  ApplicationServiceImpl implements ApplicationService {

    public static List<MenuResponse> adminMenu;

    @Value("${secret.key}")
    private String secretKey;

    @Value("${auth.token.param.name}")
    private String authTokenParamName;

    @Value("${storage.app-url}")
    private String storageAppUrl;

    @Value("${image.upload.folder}")
    private String imageUploadFolder;

    @Value("${user.folder.name}")
    private String userFolderName;

    @Value("${image.folder.name}")
    private String imageFolderName;

    @Value("${profile.image.name}")
    private String profileImageName;

    @Value("${profile.small.image.name}")
    private String profileSmallImageName;

    @Value("${profile.medium.image.name}")
    private String profileMediumImageName;

    @Value("${profile.large.image.name}")
    private String profileLargeImageName;

    @Value("${album.image.name}")
    private String albumImageName;

    @Value(value = "classpath:13thStep_Push_Dev_Certificates.p12")
    private Resource pushCertificationResource;

    @Value("${p12.file.password}")
    private String p12FilePassword;

    @Value("${web.app.port}")
    private String webAppPort;

    @Value("${web.app.name}")
    private String webAppName;

    @Value("${storage.app-name}")
    private String storageAppName;

    @Value("${app.name}")
    private String appName;

    /*@Value("${spring.mail.properties.mail.smtp.reply}")
    private String replyEmailAddress;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmailAddress;

    @Value("${spring.mail.properties.mail.smtp.from.alias}")
    private String fromEmailName;*/

    static {
        adminMenu = new ArrayList<>();
        adminMenu.add(new MenuResponse("Home", "/home"));
        adminMenu.add(new MenuResponse("Users", "/users"));
        adminMenu.add(new MenuResponse("Programs", "/programs"));
        adminMenu.add(new MenuResponse("Notifications", "/notifications"));
        adminMenu.add(new MenuResponse("User Matches", "/userMatches"));
        adminMenu.add(new MenuResponse("Match Decision", "/matchDecision"));
        adminMenu.add(new MenuResponse("Location", "/cities"));
        adminMenu.add(new MenuResponse("Devices", "/userDevices"));
        adminMenu.add(new MenuResponse("ReportAbuse", "/reportAbuse"));
        adminMenu.add(new MenuResponse("Admin User", "/admins"));
        adminMenu.add(new MenuResponse("Logout", "/logout"));
    }

    private HttpServletRequest request;

    @Autowired
    public ApplicationServiceImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public List<MenuResponse> getMainMenu(Role role) {
        List<MenuResponse> mainMenu = null;
        switch (role) {
            case ADMIN:
                mainMenu = adminMenu;
                break;
        }
        return mainMenu;
    }

    @Override
    public String getStorageAppUrl() {
        return this.storageAppUrl;
    }

    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

    public String getAuthTokenParamName() {
        return this.authTokenParamName;
    }

    @Override
    public String getImageUploadFolder() {
        return this.imageUploadFolder;
    }

    @Override
    public String getUserFolderName() {
        return this.userFolderName;
    }

    @Override
    public String getProfileImageName() {
        return this.profileImageName;
    }

    @Override
    public String getAlbumImageName() {
        return this.albumImageName;
    }

    public static List<MenuResponse> getAdminMenu() {
        return adminMenu;
    }

    public String getImageFolderName() {
        return imageFolderName;
    }

    @Override
    public String getProfileLargeImageName() {
        return profileLargeImageName;
    }

    @Override
    public String getProfileMediumImageName() {
        return profileMediumImageName;
    }

    @Override
    public String getProfileSmallImageName() {
        return profileSmallImageName;
    }

    @Override
    public Resource getPushCertificationResource() {
        return this.pushCertificationResource;
    }

    @Override
    public String getP12FilePassword() {
        return this.p12FilePassword;
    }

    @Override
    public String getWebAppPort() {
        return this.webAppPort;
    }

    @Override
    public String getWebAppName() {
        if(StringUtils.hasText(webAppName)) {
            return "/" + webAppName.trim();
        }
        return webAppName;
    }

    public String getStorageAppName() {
        if(StringUtils.hasText(storageAppName)) {
            return "/" + storageAppName.trim();
        }
        return storageAppName;
    }

    @Override
    public String getAppName() {
        if(StringUtils.hasText(appName)) {
            return "/" + appName.trim();
        }
        return appName;
    }

   /* @Override
    public String getFromEmailAddress() {
        return this.fromEmailAddress;
    }

    @Override
    public String getFromEmailName() {
        return this.fromEmailName;
    }

    @Override
    public String getReplyEmailAddress() {
        return this.replyEmailAddress;
    }*/

    @Override
    public String getAppLink() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getAppName();
    }

    @Override
    public String getWebAppLink() {
        return request.getScheme() + "://" + request.getServerName() + ":" + getWebAppPort() + getWebAppName();
    }

    @Override
    public String getStorageAppLink() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getStorageAppName();
    }

}
