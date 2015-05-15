package com.web.entity;

import com.web.service.WebEntityManager;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Entity()
@Table()
@NamedQueries({
    
    @NamedQuery(name = "WebProduct.findByLibele",query = "SELECT p FROM WebProduct p WHERE p.libele LIKE CONCAT('%', ?1,'%')" ),
    @NamedQuery(name = "WebProduct.findMatchedLibele",query = "SELECT p FROM WebProduct p WHERE p.libele=?1" ),
    @NamedQuery(name = "WebProduct.findByCategory",query = "SELECT p FROM WebProduct p WHERE p.category.id= ?1" ),
    @NamedQuery(name = "WebProduct.findByLibeleAndCategory",query = "SELECT p FROM WebProduct p WHERE p.libele LIKE CONCAT('%', ?1,'%') AND p.category.id= ?2" ),
})
public class WebProduct extends WebEntity{
    
    @Column(nullable = false)
    private String description;
    
    @NotNull
    @Column(nullable = false,unique = true)
    private String libele;
    
    @NotNull
    @Column(precision = 4,scale = 2,nullable = false)
    private double prix;
    
    @NotNull
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WebCategory category;
    
    @Column
    private long quantite;

    public WebProduct() {
    }

    public WebProduct(HttpServletRequest request) {
        super(request);
        if (request.getParameter("category") != null) {
            WebEntityManager<WebCategory> manager=new WebEntityManager(WebCategory.class);
            WebCategory categor=manager.find(Long.valueOf(request.getParameter("category")));
            this.category = categor;
        }
        if (request.getParameter("prix") != null) {
            this.prix = Double.valueOf(request.getParameter("prix"));
        }
        if (request.getParameter("quantite") != null) {
            this.quantite = Long.valueOf(request.getParameter("quantite"));
        }
        if (request.getParameter("libele") != null) {
            this.libele = request.getParameter("libele");
        }
        if (request.getParameter("description") != null) {
            this.description = request.getParameter("description");
        }
    }
    
    public String getDescription() {
        return description;
    }

    public WebProduct setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLibele() {
        return libele;
    }
    public WebProduct setLibele(String libele) {
        this.libele = libele;
        return this;
    }

    public double getPrix() {
        return prix;
    }

    public WebProduct setPrix(double prix) {
        this.prix = prix;
        return this;
    }

    public WebCategory getCategory() {
        return category;
    }

    public WebProduct setCategory(WebCategory category) {
        this.category = category;
        return this;
    }

    public long getQuantite() {
        return quantite;
    }

    public WebProduct setQuantite(long quantite) {
        this.quantite = quantite;
        return this;
    }
    
    @Override
    public String toString() {
        return this.libele;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WebProduct){
            WebProduct product=(WebProduct)obj;
            return product.getLibele().equals(getLibele());
        }
        return false;
    }
}
