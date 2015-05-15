package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

@Entity
@Table
public class WebApplication extends WebEntity {

    
    @Column(unique = true)
    private boolean offline;

    @Column(unique = true)
    private String server;

    @Column(unique = true)
    private String port;

    @Column(unique = true)
    private String database;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;

    public WebApplication(){
        
    }
    public WebApplication(HttpServletRequest request){
        super(request);
        if (request.getParameter("offline") != null) {
            this.offline = true;
        }
        if (request.getParameter("server") != null) {
            this.server = request.getParameter("server");
        }
        if (request.getParameter("port") != null) {
            this.port = request.getParameter("port");
        }
        if (request.getParameter("database") != null) {
            this.database = request.getParameter("database");
        }
        if (request.getParameter("username") != null) {
            this.username = request.getParameter("username");
        }
        if (request.getParameter("password") != null) {
            this.password = request.getParameter("password");
        }
    }
    public boolean isOffline() {
        return offline;
    }

    public WebApplication setOffline(boolean offline) {
        this.offline = offline;
        return this;
    }


    public String getServer() {
        return server;
    }

    public WebApplication setServer(String server) {
        this.server = server;
        return this;
    }

    public String getPort() {
        return port;
    }

    public WebApplication setPort(String port) {
        this.port = port;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public WebApplication setDatabase(String database) {
        this.database = database;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public WebApplication setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WebApplication setPassword(String password) {
        this.password = password;
        return this;
    }

}
