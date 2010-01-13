package us.gibb.dev.gwt.demo.client.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Recipe.Duration;

public class RecipesResult implements Result {
    private static final long serialVersionUID = -7015943059054956767L;
    private ArrayList<Row> recipes;

    RecipesResult() {
    }

    public RecipesResult(ArrayList<Row> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Row> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Row> recipes) {
        this.recipes = recipes;
    }
    
    public static class Row implements Serializable {
        private static final long serialVersionUID = 4702718034253662398L;
        public Long id;
        public String name;
        public Date createdDate;
        public Duration duration;
        
        public Row() {
        }
    }
    

}
