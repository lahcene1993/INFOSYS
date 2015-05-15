package com.web.entity;

import com.web.service.WebEntityManager;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
@Entity
@Table
@NamedQueries(value = {
    @NamedQuery(name = "WebClient.findByNom",query = "SELECT  c FROM WebClient c WHERE c.nom LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebClient.findByIdentifiant",query = "SELECT c FROM WebClient c WHERE c.identifiantFiscal LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebClient.findByEmail",query = "SELECT c FROM WebClient c WHERE c.email =?1 " ),
    @NamedQuery(name = "WebClient.findByTelephone",query = "SELECT c FROM WebClient c WHERE c.telephoneMobile LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebClient.findByFixe",query = "SELECT c FROM WebClient c WHERE c.telephoneFixe LIKE CONCAT('%', ?1,'%')" )
})
public class WebClient extends WebEntity {
    
    @NotNull
    @Column(nullable = false)
    private String nom, identifiantFiscal, ville, codePostal, pays, fax,adress;
    @NotNull
    @Column(nullable = false,unique = true)
    private String email, telephoneMobile, telephoneFixe;
    
    public WebClient(){}
    
    public WebClient(HttpServletRequest request) {
        super(request);
        if (request.getParameter("codePostal") != null) {
            this.codePostal = request.getParameter("codePostal");
        }
        if (request.getParameter("ville") != null) {
            this.ville = request.getParameter("ville");
        }
        if (request.getParameter("fax") != null) {
            this.fax = request.getParameter("fax");
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
        if (request.getParameter("identifiantFiscal") != null) {
            this.identifiantFiscal = request.getParameter("identifiantFiscal");
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

    public WebClient setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public String getEmail() {
        return email;
    }

    public WebClient setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public WebClient setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public WebClient setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public String getPays() {
        return pays;
    }

    public WebClient setPays(String pays) {
        this.pays = pays;
        return this;
    }

    public String getFax() {
        return fax;
    }

    public WebClient setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public WebClient setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }
    public WebClient setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }
    public String getTelephoneFixe() {
        return telephoneFixe;
    }
    public WebClient setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }

    public WebClient setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
        return this;
    }
    
    
    @PostPersist
    public void trrigerAfterInsert(){
       WebEntityManager<WebUser> userManager=new WebEntityManager<>(WebUser.class);
       WebUser user=new WebUser();
       user
               .setPassword(telephoneMobile)
               .setRole(WebRole.CLIENT)
               .setUsername(email)
               .setEmail(email);
       userManager.create(user);
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
    
}