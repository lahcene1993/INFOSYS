package com.web.entity;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

@Entity
public class WebInformation extends WebEntity{
    
    private String nom;
    
    private String telephoneFixe,faxe,identifiantFiscal,registreCommerce;
    
    private String adresse,ville,codePostale,pays;

    public WebInformation() {
    }

    

    public String getNom() {
        return nom;
    }

    public WebInformation setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public WebInformation setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }

    public String getFaxe() {
        return faxe;
    }

    public WebInformation setFaxe(String faxe) {
        this.faxe = faxe;
        return this;
    }

    public String getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public WebInformation setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
        return this;
    }

    public String getRegistreCommerce() {
        return registreCommerce;
    }

    public WebInformation setRegistreCommerce(String registreCommerce) {
        this.registreCommerce = registreCommerce;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public WebInformation setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public WebInformation setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public WebInformation setCodePostale(String codePostale) {
        this.codePostale = codePostale;
        return this;
    }

    public String getPays() {
        return pays;
    }

    public WebInformation setPays(String pays) {
        this.pays = pays;
        return this;
    }    
    
    public WebInformation(HttpServletRequest request){
        super(request);
        if (request.getParameter("faxe") != null) {
            this.faxe = request.getParameter("faxe");
        }
        if (request.getParameter("adresse") != null) {
            this.adresse = request.getParameter("adresse");
        }
        if (request.getParameter("codePostale") != null) {
            this.codePostale = request.getParameter("codePostale");
        }
        if (request.getParameter("identifiantFiscal") != null) {
            this.identifiantFiscal = request.getParameter("identifiantFiscal");
        }
        if (request.getParameter("nom") != null) {
            this.nom = request.getParameter("nom");
        }
        if (request.getParameter("pays") != null) {
            this.pays = request.getParameter("pays");
        }
        if (request.getParameter("registreCommerce") != null) {
            this.registreCommerce = request.getParameter("registreCommerce");
        }
        if (request.getParameter("telephoneFixe") != null) {
            this.telephoneFixe = request.getParameter("telephoneFixe");
        }
        if (request.getParameter("ville") != null) {
            this.ville = request.getParameter("ville");
        }
    }
}
