package com.web.entity;

import com.web.service.WebEntityManager;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;


@Entity
@Table
@NamedQueries(value = {
    @NamedQuery(name = "WebManager.findByNom",query = "SELECT  c FROM WebManager c WHERE c.nom LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebManager.findByEmail",query = "SELECT c FROM WebManager c WHERE c.email LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebManager.findByTelephone",query = "SELECT c FROM WebManager c WHERE c.telephoneMobile LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebManager.findByFixe",query = "SELECT c FROM WebManager c WHERE c.telephoneFixe LIKE CONCAT('%', ?1,'%')" )
})
public class WebManager extends WebEntity {
    
    @NotNull
    @Column(nullable = false)
    private String nom, ville, codePostal, pays,adress;
    
    @NotNull
    @Column(nullable = false,unique = true)
    private String email, telephoneMobile, telephoneFixe;
    @Column
    private boolean admin;
    
    public WebManager(){}
    
    public WebManager(HttpServletRequest request) {
        super(request);
        if (request.getParameter("codePostal") != null) {
            this.codePostal = request.getParameter("codePostal");
        }
        if (request.getParameter("ville") != null) {
            this.ville = request.getParameter("ville");
        }
        
        if (request.getParameter("adress") != null) {
            this.adress = request.getParameter("adress");
        }
        if (request.getParameter("pays") != null) {
            this.pays = request.getParameter("pays");
        }
        if (request.getParameter("nom") != null) {
            this.nom = request.getParameter("nom");
        }
        
        if (request.getParameter("email") != null) {
            this.email = request.getParameter("email");
        }
        if (request.getParameter("telephoneMobile") != null) {
            this.telephoneMobile = request.getParameter("telephoneMobile");
        }
        if (request.getParameter("telephoneFixe") != null) {
            this.telephoneFixe = request.getParameter("telephoneFixe");
        }
        
    }

    public String getNom() {
        return nom;
    }

    public WebManager setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public WebManager setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public WebManager setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public WebManager setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public String getPays() {
        return pays;
    }

    public WebManager setPays(String pays) {
        this.pays = pays;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public WebManager setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }
    public WebManager setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }
    public String getTelephoneFixe() {
        return telephoneFixe;
    }
    public WebManager setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }    

    public boolean isAdmin() {
        return admin;
    }

    public WebManager setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }
    
    
   @PostPersist
    public void trrigerAfterInsert(){
       WebEntityManager<WebUser> userManager=new WebEntityManager<>(WebUser.class);
       WebUser user=new WebUser();
       user
               .setPassword(telephoneMobile)
               .setUsername(email)
               .setEmail(email);
       if(admin){
           user.setRole(WebRole.ADMIN);
       }else {
           user.setRole(WebRole.MANAGER);
       }
       userManager.create(user);
    }
    
    @Override
    public String toString() {
        return this.nom;
    }
}