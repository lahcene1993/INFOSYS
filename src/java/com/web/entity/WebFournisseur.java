package com.web.entity;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Entity
@Table
@NamedQueries(value = {
    @NamedQuery(name = "WebFournisseur.findByNom",query = "SELECT  c FROM WebFournisseur c WHERE c.nom LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebFournisseur.findByIdentifiant",query = "SELECT c FROM WebFournisseur c WHERE c.identifiantFiscal LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebFournisseur.findByEmail",query = "SELECT c FROM WebFournisseur c WHERE c.email LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebFournisseur.findByTelephone",query = "SELECT c FROM WebFournisseur c WHERE c.telephoneMobile LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebFournisseur.findByFixe",query = "SELECT c FROM WebFournisseur c WHERE c.telephoneFixe LIKE CONCAT('%', ?1,'%')" )
})
public class WebFournisseur extends WebEntity {
    
    @NotNull
    @Column(nullable = false)
    private String nom, identifiantFiscal, ville, codePostal, pays, fax,adress;
    
    @NotNull
    @Column(nullable = false,unique = true)
    private String email, telephoneMobile, telephoneFixe;
    
    public WebFournisseur(){}
    
    public WebFournisseur(HttpServletRequest request) {
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

    public WebFournisseur setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public WebFournisseur setPrenom(String prenom) {
        this.identifiantFiscal = prenom;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public WebFournisseur setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public WebFournisseur setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public WebFournisseur setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public String getPays() {
        return pays;
    }

    public WebFournisseur setPays(String pays) {
        this.pays = pays;
        return this;
    }

    public String getFax() {
        return fax;
    }

    public WebFournisseur setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public WebFournisseur setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }
    public WebFournisseur setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }
    public String getTelephoneFixe() {
        return telephoneFixe;
    }
    public WebFournisseur setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }

    public WebFournisseur setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
        return this;
    }
    
    @Override
    public String toString() {
        return this.nom;
    }
}