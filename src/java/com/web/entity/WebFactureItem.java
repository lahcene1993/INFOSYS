package com.web.entity;

import com.web.service.WebEntityManager;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Entity
@Table
public class WebFactureItem extends WebEntity {

    
    @NotNull
    @Column()
    private int quantite;

    @NotNull
    @Column()
    private double prix;

    @NotNull
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WebProduct product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "facture", nullable = false)
    private WebFacture facture;

    public WebFactureItem() {
    }

    public WebFactureItem(HttpServletRequest request) {
        super(request);
        if (request.getParameter("prix") != null && !request.getParameter("prix").equals("")) {
            this.prix = Double.valueOf(request.getParameter("prix"));
        }
        if (request.getParameter("quantite") != null && !request.getParameter("quantite").equals("")) {
            this.quantite = Integer.valueOf(request.getParameter("quantite"));
        }
        if (request.getParameter("product") != null && !request.getParameter("product").equals("")) {
            WebProduct product1 = new WebEntityManager<WebProduct>(WebProduct.class).findOne("WebProduct.findMatchedLibele", request.getParameter("product"));
            this.product = product1;
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public WebFactureItem setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public double getPrix() {
        return prix;
    }

    public WebFactureItem setPrix(double prix) {
        this.prix = prix;
        return this;
    }

    public WebProduct getProduct() {
        return product;
    }

    public WebFactureItem setProduct(WebProduct product) {
        this.product = product;
        return this;
    }

    public WebFacture getFacture() {
        return facture;
    }

    public WebFactureItem setFacture(WebFacture facture) {
        this.facture = facture;
        return this;
    }

    @Override
    public boolean isValid() {
        if (quantite > product.getQuantite() || prix < product.getPrix()) {
            return false;
        }
        return super.isValid();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WebFactureItem){
            WebFactureItem item=(WebFactureItem)obj;
            return item.getFacture().equals(getFacture()) && item.getProduct().equals(getProduct());
        }
        return false;
    }
    
}
