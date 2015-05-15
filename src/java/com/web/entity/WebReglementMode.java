package com.web.entity;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
@Entity
@Table
public class WebReglementMode extends WebEntity {
    
    @NotNull
    @Column(unique = true,nullable = false)
    private String name;

    public WebReglementMode() {
    }

    public WebReglementMode(HttpServletRequest request) {
        super(request);
        if (request.getParameter("name") != null) {
            this.name = request.getParameter("name");
        }
    }
    
    public String getName() {
        return name;
    }

    public WebReglementMode setName(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}