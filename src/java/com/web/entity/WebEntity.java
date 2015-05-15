package com.web.entity;

import com.web.IValidation;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Version;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "WebEntity.countProduct", 
            query = "SELECT NEW com.web.bean.WebState("
                    + "(SELECT COUNT(P) FROM  WebProduct AS p) ,"
                    + "(SELECT COUNT(ct) FROM  WebCategory AS ct),"
                    + "(SELECT COUNT(cl) FROM  WebClient AS cl),"
                    + "(SELECT COUNT(fr) FROM  WebFournisseur AS fr),"
                    + "(SELECT COUNT(ft) FROM  WebFacture AS ft WHERE ft.type=com.web.entity.WebType.FACTURE AND CURRENT_DATE = CURRENT_DATE ),"
                    + "(SELECT COUNT(ft) FROM  WebFacture AS ft WHERE ft.type=com.web.entity.WebType.LIVRAISON),"
                    + "(SELECT COUNT(ft) FROM  WebFacture AS ft WHERE ft.type=com.web.entity.WebType.COMMANDE),"
                    + "(SELECT COUNT(ft) FROM  WebFacture AS ft WHERE ft.type=com.web.entity.WebType.DEVIS)"
                    + ") FROM WebProduct AS pr ")
})
public abstract class WebEntity implements Serializable, IValidation {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @OrderBy(value = "DESC")
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public WebEntity() {

    }

    public WebEntity(HttpServletRequest request) {
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            this.id = Long.valueOf(request.getParameter("id"));
        }
        if (request.getParameter("version") != null && !request.getParameter("version").equals("")) {
            this.version = Long.valueOf(request.getParameter("version"));
        }
    }

    @Override
    public boolean isValid() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(this).isEmpty();
    }
}
