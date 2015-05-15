package com.web.service;

import com.web.entity.WebUser;

public class WebAuthManager {

    private final WebEntityManager<WebUser> manager;
    private WebUser user;

    public WebAuthManager() {
        manager = new WebEntityManager<>(WebUser.class);
    }

    public boolean isAuthenticated(WebUser user) {
        this.user = user;
        return manager.findOne("WebUser.findByUsernameAndPassword", user.getUsername(), user.getPassword()) != null;
    }

    public WebUser getAutenticatedUser() {
        return manager.findOne("WebUser.findByUsernameAndPassword", user.getUsername(), user.getPassword());
    }
}
