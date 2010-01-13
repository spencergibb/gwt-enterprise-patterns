package us.gibb.dev.gwt.demo.client;

import java.util.ArrayList;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.GetRecipesCommand;
import us.gibb.dev.gwt.demo.client.command.RecipesResult;
import us.gibb.dev.gwt.demo.model.Recipe;
import us.gibb.dev.gwt.location.Location;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RecipesViewImpl extends AbstractWidgetView<CommandEventBus> implements RecipesPresenter.View {

    private static final int NUM_COLS = 3;

    public interface RecipesViewUiBinder extends UiBinder<Widget, RecipesViewImpl> { }

    @UiField HasClickHandlers button;
    @UiField TextBox name;
    @UiField TabLayoutPanel tabPanel;
    @UiField Grid grid;
    @UiField Hyperlink addLink;
    private int selectedTab;
    
    @Inject
    public RecipesViewImpl(final CommandEventBus eventBus, final RecipePresenter recipe,
            RecipesViewUiBinder uiBinder) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));

        addLink.setTargetHistoryToken(recipe.getView().getLocation());
        
        tabPanel.add(recipe.getView().getImpl(), "Edit");
        
        tabPanel.selectTab(0);
        
        initGrid();
        
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            public void onSelection(SelectionEvent<Integer> event) {
                if (event.getSelectedItem() != selectedTab) {
                    selectedTab = event.getSelectedItem();
                    switch (event.getSelectedItem()) {
                    case 1:
                        eventBus.changeLocationIfNotCurrent(recipe.getView().getLocation());
                        break;
                    default:
                        eventBus.changeLocation(getLocation());
                        break;
                    }
                }
            }});
        
        eventBus.add(new Location.Handler(getLocation()) {
            public void handle(Location location) {
                if (selectedTab != 0) {
                    tabPanel.selectTab(0);
                }
                if (location.getParam(0) != null) {
                    name.setText(location.getParam(0));
                }
            }});
        
        eventBus.add(new Location.Handler(recipe.getView().getLocation()) {
            public void handle(Location location) {
                if (selectedTab != 1) {
                    tabPanel.selectTab(1);
                }
            }});
        
        eventBus.add(new ResultEvent.Handler<RecipesResult>(GetRecipesCommand.class){
            public void handle(ResultEvent<RecipesResult> event) {
                populateGrid(event.getResult().getRecipes());
            }});
    }

    private void initGrid() {
        grid.resize(1, NUM_COLS);
        grid.setText(0, 0, "Id");
        grid.setText(0, 1, "Recipe");
        grid.setText(0, 2, "Actions");
    }
    
    protected void populateGrid(ArrayList<Recipe> recipes) {
        grid.resize(recipes.size() + 1, NUM_COLS);
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            int row = i+1;
            String id = recipe.getId().toString();
            grid.setHTML(row, 0, getHyperLinkHTML(id, "edit", id));
            grid.setText(row, 1, recipe.getName());
            grid.setHTML(row, 2, getHyperLinkHTML("Delete", "recipes", "delete", id));
        }
    }

    private String getHyperLinkHTML(String text, String location, String ... params) {
        return new Hyperlink(text, new Location(location, params).toString()).toString();
    }

    public HasClickHandlers getButton() {
        return button;
    }
    
    @Override
    public HasText getName() {
        return name;
    }
    
    @Override
    public HasKeyPressHandlers getNameKeys() {
        return name;
    }
    
    @Override
    public String getLocation() {
        return "recipes";
    }

}
