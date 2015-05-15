package com.web.bean;
public class WebState {
    private Number products,categories,clients,fournisseurs,factures,livraisons,commandes,devis;

    public Number getCategories() {
        return categories;
    }

    public void setCategories(Number categories) {
        this.categories = categories;
    }

    public Number getClients() {
        return clients;
    }

    public void setClients(Number clients) {
        this.clients = clients;
    }

    public Number getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(Number fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public Number getFactures() {
        return factures;
    }

    public void setFactures(Number factures) {
        this.factures = factures;
    }

    
    
    public Number getProducts() {
        return products;
    }

    public void setProducts(Number products) {
        this.products = products;
    }

    public WebState(Number products, Number categories, Number clients, Number fournisseurs, Number factures) {
        this.products = products;
        this.categories = categories;
        this.clients = clients;
        this.fournisseurs = fournisseurs;
        this.factures = factures;
    }

    public WebState(Number products, Number categories, Number clients, Number fournisseurs, Number factures, Number livraisons, Number commandes, Number devis) {
        this.products = products;
        this.categories = categories;
        this.clients = clients;
        this.fournisseurs = fournisseurs;
        this.factures = factures;
        this.livraisons = livraisons;
        this.commandes = commandes;
        this.devis = devis;
    }

    public Number getLivraisons() {
        return livraisons;
    }

    public void setLivraisons(Number livraisons) {
        this.livraisons = livraisons;
    }

    public Number getCommandes() {
        return commandes;
    }

    public void setCommandes(Number commandes) {
        this.commandes = commandes;
    }

    public Number getDevis() {
        return devis;
    }

    public void setDevis(Number devis) {
        this.devis = devis;
    }
    
    
}
