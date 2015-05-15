package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Entity
@Table()
@NamedQueries({
    @NamedQuery(name = "WebUser.findByUsernameAndPassword",query = "SELECT u FROM WebUser u WHERE u.username=?1 AND u.password=?2"),
    @NamedQuery(name = "WebUser.findByEmailAndPassword",query = "SELECT u FROM WebUser u WHERE u.email=?1 AND u.password=?2")
})
public class WebUser extends WebEntity {

    @NotNull
    @Column(nullable = false,unique = true)
    private String username;
    
    @NotNull
    @Column(nullable = false,unique = false)
    private String password;
    
    @NotNull
    @Column(nullable = false,unique = true)
    private String email;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private WebRole role; 
    
    public WebUser(HttpServletRequest request) {
        super(request);
        if (request.getParameter("email") != null) {
            this.email = request.getParameter("email");
        }
        if (request.getParameter("password") != null) {
            this.password = request.getParameter("password");
        }
        if (request.getParameter("username") != null) {
            this.username = request.getParameter("username");
        }
    }

    public WebUser() {
    }

    public String getUsername() {
        return username;
    }

    public WebUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WebUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public WebUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public WebRole getRole() {
        return role;
    }

    public WebUser setRole(WebRole role) {
        this.role = role;
        return this;
    }
    
    @Override
    public String toString() {
        return this.username;
    }
}
