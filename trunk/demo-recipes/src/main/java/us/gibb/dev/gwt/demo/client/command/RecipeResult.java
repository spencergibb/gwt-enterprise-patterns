package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Recipe;

public class RecipeResult implements Result {
    private static final long serialVersionUID = 1262240689867873273L;
    private Recipe recipe;

    RecipeResult() {
    }

    public RecipeResult(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    

}
