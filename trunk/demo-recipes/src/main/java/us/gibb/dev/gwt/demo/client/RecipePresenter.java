package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.GetRecipeCommand;
import us.gibb.dev.gwt.demo.client.command.RecipeResult;
import us.gibb.dev.gwt.demo.client.command.SaveRecipeCommand;
import us.gibb.dev.gwt.demo.model.Recipe;
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
        HasText getText();
    }
    
    @Inject
    protected RecipePresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);
        
        eventBus.add(new Location.Handler(view.getLocation()) {
            public void handle(Location location) {
                String id = location.getParam(0);
                if (id != null) {
                    eventBus.fire(new GetRecipeCommand(id));
                }
            }});
        
        eventBus.add(new ResultEvent.Handler<RecipeResult>(GetRecipeCommand.class) {
            public void handle(ResultEvent<RecipeResult> event) {
                Recipe recipe = event.getResult().getRecipe();
                view.getId().setText(recipe.getId().toString());
                view.getName().setText(recipe.getName());
                view.getText().setText(recipe.getText());
            }});
        
        getView().getSave().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Recipe recipe = new Recipe();
                if (view.getId().getText() != null && !view.getId().getText().isEmpty()) {
                    recipe.setId(new Long(view.getId().getText()));
                }
                recipe.setName(view.getName().getText());
                recipe.setText(view.getText().getText());
                eventBus.fire(new SaveRecipeCommand(recipe));
            }});
        
    }
}
