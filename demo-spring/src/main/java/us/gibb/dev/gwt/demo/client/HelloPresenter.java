package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.command.results.StringResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.location.HasLocation;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class HelloPresenter extends AbstractPresenter<HelloPresenter.View, CommandEventBus> {
    public interface View extends WidgetView, HasLocation {
        HasClickHandlers getButton();
        HasText getName();
        HasKeyPressHandlers getNameKeys();
        HasText getResult();
    }
    
    @Inject
    protected HelloPresenter(final CommandEventBus eventBus, View view) {
        super(eventBus, view);
        
        getView().getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                sayHello();
            }});
        
        getView().getNameKeys().addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if ((int)event.getCharCode() == 13) { //on enter key pressed
                    sayHello();
                }
            }});
        
        eventBus.add(new ResultEvent.Handler<StringResult>(SayHelloCommand.class){
            public void handle(ResultEvent<StringResult> event) {
                getView().getResult().setText(event.getResult().getString());
            }});
        
        eventBus.add(new AlertFailureEventHandler());
    }

    private void sayHello() {
        eventBus.changeLocation(getView().getLocation(), getView().getName().getText());
        eventBus.fire(new SayHelloCommand(getView().getName().getText()));
    }

}
