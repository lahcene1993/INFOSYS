package com.web.entity;
public enum WebType {
    COMMANDE,
    DEVIS,
    LIVRAISON,
    FACTURE;
    
    @Override    
    public String toString() {
        return this.name();
    }
}