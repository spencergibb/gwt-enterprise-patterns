package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.GetRecipeCommand;
import us.gibb.dev.gwt.demo.client.command.RecipeResult;
import us.gibb.dev.gwt.demo.client.command.SaveRecipeCommand;
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
                    RecipePresenter.this.recipe = null;
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

    private void populateView(Recipe r) {
        recipe = r;
        view.getId().setText(recipe.getId().toString());
        view.getName().setText(recipe.getName());
        view.getText().setText(recipe.getText());
        view.getDuration().setText(recipe.getDuration().toString());
    }

    private void save() {
        String id = view.getId().getText();
        if (id == null || id.isEmpty()) { //add
            recipe = new Recipe();
        }
        recipe.setName(view.getName().getText());
        recipe.setText(view.getText().getText());
        recipe.setDuration(Duration.valueOf(view.getDuration().getText()));
        eventBus.fire(new SaveRecipeCommand(recipe));
    }
}
