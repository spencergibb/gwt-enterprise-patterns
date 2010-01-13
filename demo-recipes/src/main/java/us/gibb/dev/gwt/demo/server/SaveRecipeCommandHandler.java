package us.gibb.dev.gwt.demo.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.demo.client.command.RecipeResult;
import us.gibb.dev.gwt.demo.client.command.SaveRecipeCommand;
import us.gibb.dev.gwt.demo.model.Recipe;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.jpa.JPACommandHandler;

import com.google.inject.Inject;

public class SaveRecipeCommandHandler extends JPACommandHandler<SaveRecipeCommand, RecipeResult> {

    @Inject
    public SaveRecipeCommandHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected RecipeResult execute(EntityManager em, SaveRecipeCommand command, Context context) throws CommandException {
        Recipe recipe = command.getRecipe();
        if (recipe != null && recipe.getId() != null) {
            /*Recipe toSave = em.find(Recipe.class, recipe.getId());
            if (toSave == null) {
                //we are trying to save one that doesn't exist
                throw new CommandException("Recipe does not exist: "+recipe.getId());
            }
            toSave.setName(recipe.getName());
            toSave.setText(recipe.getText());
            //r2.setIngredients(recipe.getIngredients());
            em.merge(toSave);*/
            recipe = em.merge(recipe);
        } else {
            em.persist(recipe);
        }
        return new RecipeResult(recipe);
    }

}