package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.demo.model.Recipe;

public class SaveRecipeCommand implements Command<RecipeResult> {
    private static final long serialVersionUID = -7944106025776530524L;
    private Recipe recipe;

    public SaveRecipeCommand() {
    }
    
    public SaveRecipeCommand(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
