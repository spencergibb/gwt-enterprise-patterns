package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.location.HasLocation;
import us.gibb.dev.gwt.location.Location;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;

public class GoodbyePresenter extends AbstractPresenter<GoodbyePresenter.View, CommandEventBus> {

    public interface View extends WidgetView, HasLocation {
        HasText getName();
        HasClickHandlers getButton();
        HasText getResult();
    }
    
    @Inject
    protected GoodbyePresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);

        eventBus.add(new Location.Handler(view.getLocation()) {
            public void handle(Location location) {
                view.getName().setText(location.getParam(0));
            }});
        
        eventBus.add(new ResultEvent.Handler<HelloResult>(GetHelloCommand.class) {
            public void handle(ResultEvent<HelloResult> event) {
                Hello hello = event.getResult().getHello();
                if (hello != null) {
                    view.getResult().setText("Said hello to "+hello.getName()+" on "+hello.getCreatedDate());
                } else {
                    view.getResult().setText("Never said hello to them");
                }
            }});
        
        view.getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fire(new GetHelloCommand(view.getName().getText()));
            }});
        
    }

}
