package us.gibb.dev.gwt.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.gilead.pojo.java5.LightEntity;

@Entity
@Table(name="ingredient")
public class Ingredient extends LightEntity {
    private static final long serialVersionUID = -1508599717060362857L;

    private Long id;
    
    private String text;

    private Date createdDate;
    
    public Ingredient() {}

    @Id
    @GeneratedValue(generator = "ingredientid_generator")
    @SequenceGenerator(name = "ingredientid_generator", sequenceName = "INGREDIENT_SEQ", allocationSize = 1)
    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @PrePersist
    public void prePersist() {
        setCreatedDate(new Date());
    }
    
    @Override
    public String toString() {
        return "Ingredient "+text+" on "+createdDate;
    }
}
