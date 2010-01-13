package us.gibb.dev.gwt.demo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.sf.gilead.pojo.java5.LightEntity;

@Entity
@Table(name="recipe")
public class Recipe extends LightEntity {
    private static final long serialVersionUID = -1508599717060362857L;
    public enum Duration {SHORT, MEDIUM, LONG};

    private Long id;
    
    private String name;

    private Duration duration = Duration.SHORT;
    
    private String text;

    private Date createdDate;
    
    private Set<Ingredient> ingredients = null;
    
    public Recipe() {}

    @Id
    @GeneratedValue(generator = "recipeid_generator")
    @SequenceGenerator(name = "recipeid_generator", sequenceName = "RECIPE_SEQ", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    
    @PrePersist
    public void prePersist() {
        setCreatedDate(new Date());
    }
    
    @Override
    public String toString() {
        return "Recipe "+name+" on "+createdDate;
    }
}
