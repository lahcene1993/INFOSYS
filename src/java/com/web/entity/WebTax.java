package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;


@Entity
@Table
public class WebTax extends WebEntity  {
    
    @NotNull
    @Column(precision = 4,scale = 2)
    private double value;

    public WebTax() {
    }

    public WebTax(HttpServletRequest request) {
        super(request);
        if (request.getParameter("value") != null) {
            this.value = Double.valueOf(request.getParameter("value"));
        }
    }
    

    public double getValue() {
        return value;
    }

    public WebTax setValue(double value) {
        this.value = value;
        return this;
    }
    
    @Override
    public String toString() {
        return this.value+"";
    }
}