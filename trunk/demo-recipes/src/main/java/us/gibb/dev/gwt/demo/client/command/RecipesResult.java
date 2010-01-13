package us.gibb.dev.gwt.demo.client.command;

import java.util.ArrayList;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Recipe;

public class RecipesResult implements Result {
    private static final long serialVersionUID = -7015943059054956767L;
    private ArrayList<Recipe> recipes;

    RecipesResult() {
    }

    public RecipesResult(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
    

}
