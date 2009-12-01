package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.client.command.SayHelloResult;
import us.gibb.dev.gwt.location.HasLocation;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class HelloPresenter extends AbstractPresenter<HelloPresenter.View, CommandEventBus> {
    public interface View extends WidgetView, HasLocation {
        HasClickHandlers getButton();
        HasText getName();
        HasText getResult();
    }
    
    @Inject
    protected HelloPresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);
        
        view.getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.changeLocation(view.getLocation(), view.getName().getText());
                eventBus.fire(new SayHelloCommand(view.getName().getText()));
            }});
        
        eventBus.add(new ResultEvent.Handler<SayHelloResult>(SayHelloResult.class){
            public void handle(ResultEvent<SayHelloResult> event) {
                view.getResult().setText(event.getResult().getHello().toString());
            }});
        
        eventBus.add(new AlertFailureEventHandler());
    }

}
