package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.demo.client.command.GetRecipesCommand;
import us.gibb.dev.gwt.location.HasLocation;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class RecipesPresenter extends AbstractPresenter<RecipesPresenter.View, CommandEventBus> {
    public interface View extends WidgetView, HasLocation {
        HasClickHandlers getButton();
        HasText getName();
        HasKeyPressHandlers getNameKeys();
    }
    
    @Inject
    protected RecipesPresenter(final CommandEventBus eventBus, View view) {
        super(eventBus, view);
        
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                getRecipes();
            }});
        
        getView().getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getRecipes();
            }});
        
        getView().getNameKeys().addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if ((int)event.getCharCode() == 13) { //on enter key pressed
                    getRecipes();
                }
            }});
        
        eventBus.add(new AlertFailureEventHandler());
    }

    private void getRecipes() {
        //eventBus.changeLocation(getView().getLocation());
        eventBus.fire(new GetRecipesCommand());
    }

}
