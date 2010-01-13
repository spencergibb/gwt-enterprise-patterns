package us.gibb.dev.gwt.demo.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import us.gibb.dev.gwt.demo.client.command.GetRecipeCommand;
import us.gibb.dev.gwt.demo.client.command.RecipeResult;
import us.gibb.dev.gwt.demo.model.Recipe;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.jpa.JPACommandHandler;

import com.google.inject.Inject;

public class GetRecipeCommandHandler extends JPACommandHandler<GetRecipeCommand, RecipeResult> {

    @Inject
    public GetRecipeCommandHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected RecipeResult execute(EntityManager em, GetRecipeCommand command, Context context) {
        Recipe recipe = em.find(Recipe.class, command.getId());
        if (recipe != null) {
            recipe.getIngredients(); //force load, lazy loading so we can send across wire without ingredients
        }
        return new RecipeResult(recipe);
    }

}
