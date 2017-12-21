package com.example.demo.entity;

public class AuthInfo {
	 private int userId;
	    private Role role;
	    private String origin;
	    private String token;

	    private static final String DELIMITER = "!";

	    public AuthInfo(String origin, int userId, Role role) {
	        this.origin = origin;
	        this.userId = userId;
	        this.role = role;
	    }

	    @Override
	    public String toString() {
	        return origin + DELIMITER + userId + DELIMITER + role;
	    }

	    public static AuthInfo parse(String string) {
	        AuthInfo info = null;
	        String[] tokenInfo = string.split(DELIMITER);
	        try {
	            info = new AuthInfo(tokenInfo[0], Integer.parseInt(tokenInfo[1]), Role.valueOf(tokenInfo[2]));
	        } catch (Exception e) {
	            return null;
	        }
	        if(info != null && (info.getUserId() == 0 || info.getRole() == null && info.getOrigin() == null)) {
	            return null;
	        }
	        return info;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }

	    public String getOrigin() {
	        return origin;
	    }

	    public void setOrigin(String origin) {
	        this.origin = origin;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
}
