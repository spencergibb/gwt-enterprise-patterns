package us.gibb.dev.gwt.demo.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.GetRecipeCommand;
import us.gibb.dev.gwt.demo.client.command.RecipeResult;
import us.gibb.dev.gwt.demo.client.command.SaveRecipeCommand;
import us.gibb.dev.gwt.demo.model.Ingredient;
import us.gibb.dev.gwt.demo.model.Recipe;
import us.gibb.dev.gwt.demo.model.Recipe.Duration;
import us.gibb.dev.gwt.location.HasLocation;
import us.gibb.dev.gwt.location.Location;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class RecipePresenter extends AbstractPresenter<RecipePresenter.View, CommandEventBus> {
    public interface View extends WidgetView, HasLocation {
        HasClickHandlers getSave();
        HasText getId();
        HasText getName();
        HasText getDuration();
        HasText getText();
        HasText getIngredient1();
        HasText getIngredient2();
        HasText getIngredient3();
    }
    
    private Recipe recipe;
    
    @Inject
    protected RecipePresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);
        
        eventBus.add(new Location.Handler(view.getLocation()) {
            public void handle(Location location) {
                String id = location.getParam(0);
                if (id != null) {
                    eventBus.fire(new GetRecipeCommand(id));
                } else {
                    resetView();
                }
            }});
        
        eventBus.add(new ResultEvent.Handler<RecipeResult>(GetRecipeCommand.class) {
            public void handle(ResultEvent<RecipeResult> event) {
                populateView(event.getResult().getRecipe());
            }});
        
        getView().getSave().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                save();
            }});
        
    }

    protected void resetView() {
        recipe = null;
        view.getId().setText(null);
        view.getName().setText(null);
        view.getText().setText(null);
        view.getIngredient1().setText(null);
        view.getIngredient2().setText(null);
        view.getIngredient3().setText(null);
        view.getDuration().setText(Duration.SHORT.toString());
    }

    private void populateView(Recipe r) {
        resetView();
        if (r == null) {
            return; //send not found msg
        }
        recipe = r;
        view.getId().setText(recipe.getId().toString());
        view.getName().setText(recipe.getName());
        view.getText().setText(recipe.getText());
        view.getDuration().setText(recipe.getDuration().toString());
        Set<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients == null || ingredients.isEmpty()) {
            return;
        }
        int i = 1;
        for (Ingredient ingredient : ingredients) {
            switch (i) {
            case 1:
                view.getIngredient1().setText(ingredient.getText());
                break;
            case 2:
                view.getIngredient2().setText(ingredient.getText());
                break;
            case 3:
                view.getIngredient3().setText(ingredient.getText());
                break;
            default:
                break;
            }
            i++;
        }
    }

    private void save() {
        String id = view.getId().getText();
        if (isEmpty(id)) { //add
            recipe = new Recipe();
        }
        recipe.setName(view.getName().getText());
        recipe.setText(view.getText().getText());
        recipe.setDuration(Duration.valueOf(view.getDuration().getText()));

        //bad move here
        Set<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients == null) {
            ingredients = new HashSet<Ingredient>();
        }
        if (!isEmpty(view.getIngredient1().getText())) {
            add(ingredients, 1, view.getIngredient1().getText());
        }
        if (!isEmpty(view.getIngredient2().getText())) {
            add(ingredients, 2, view.getIngredient2().getText());
        }
        if (!isEmpty(view.getIngredient3().getText())) {
            add(ingredients, 3, view.getIngredient3().getText());
        }
        recipe.setIngredients(ingredients);
        eventBus.fire(new SaveRecipeCommand(recipe));
    }

    private void add(Set<Ingredient> ingredients, int i, String text) {
        if (ingredients.size() >= i) {
            //get i'th-1 ingredient and edit
            Ingredient ingredient = new ArrayList<Ingredient>(ingredients).get(i-1);
            ingredient.setText(text);
        } else {
            ingredients.add(new Ingredient(text));
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
