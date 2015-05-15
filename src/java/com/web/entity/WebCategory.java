package com.web.entity;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
@Entity()
@Table()
@NamedQuery(name = "WebCategory.findByNom", 
    query = 
        "SELECT c FROM WebCategory AS c WHERE c.nom LIKE CONCAT('%', ?1,'%')")
public class WebCategory extends WebEntity {
    @Column(unique = true, nullable = false)
    @NotNull()
    private String nom;
    public WebCategory() {}
    public WebCategory(HttpServletRequest request) {
        super(request);
        if (request.getParameter("nom") != null) {
            this.nom = request.getParameter("nom");
        }
    }
    public String getNom() {
        return nom;
    }
    public WebCategory setNom(String nom) {
        this.nom = nom;
        return this;
    }
    @Override
    public String toString() {
        return this.nom;
    }
}
