package com.web.entity;

import com.web.exception.WebException;
import com.web.service.WebEntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Entity()
@Table()
public class WebStock extends WebEntity {

    @NotNull
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne(optional = false,cascade = CascadeType.REFRESH , fetch = FetchType.LAZY)
    private WebProduct product;

    @NotNull
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WebFournisseur fournisseur;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    @javax.persistence.OrderBy(value = "DESC")
    private Date date;

    @NotNull
    @Min(value = 1)
    @Column
    private int quantite;
    
    @NotNull
    @Column()
    private double prix;

    @Column
    private String remarque;

    public WebStock() {
    }

    public WebStock(HttpServletRequest request) {
        super(request);
        if (request.getParameter("quantite") != null) {
            this.quantite = Integer.valueOf(request.getParameter("quantite"));
        }
        if (request.getParameter("remarque") != null) {
            this.remarque = (request.getParameter("remarque"));
        }
        if (request.getParameter("date") != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                this.date = sdf.parse(request.getParameter("date"));
            } catch (Exception e) {
            }
        }
        if (request.getParameter("prix") != null) {
            this.prix = Double.valueOf(request.getParameter("prix"));
        }
        if (request.getParameter("fournisseur") != null) {
            WebEntityManager<WebFournisseur> manager = new WebEntityManager<>(WebFournisseur.class);
            WebFournisseur fournisseu = manager.find(Long.valueOf(request.getParameter("fournisseur")));
            this.fournisseur = fournisseu;
        }
        if (request.getParameter("product") != null) {
            WebEntityManager<WebProduct> manager = new WebEntityManager<>(WebProduct.class);
            WebProduct produc = manager.findOne("WebProduct.findMatchedLibele",request.getParameter("product"));
            this.product = produc;
        }
    }

    public WebProduct getProduct() {
        return product;
    }

    public WebStock setProduct(WebProduct product) {
        this.product = product;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public WebStock setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getQuantite() {
        return quantite;
    }

    public WebStock setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public String getRemarque() {
        return remarque;
    }

    public WebFournisseur getFournisseur() {
        return fournisseur;
    }

    public WebStock setFournisseur(WebFournisseur fournisseur) {
        this.fournisseur = fournisseur;
        return this;
    }

    public WebStock setRemarque(String remarque) {
        this.remarque = remarque;
        return this;
    }

    public double getPrix() {
        return prix;
    }

    public WebStock setPrix(double prix) {
        this.prix = prix;
        return this;
    }
    
    @PostPersist
    public void trrigerAfterInsert() throws WebException {
        WebEntityManager<WebProduct> manager = new WebEntityManager<>(WebProduct.class);
        product.setQuantite(product.getQuantite() + quantite);
        manager.edit(product);
    }
    
}
