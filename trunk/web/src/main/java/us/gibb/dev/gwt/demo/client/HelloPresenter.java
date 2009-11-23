package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.event.HasLocation;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

public class HelloPresenter extends AbstractPresenter<HelloPresenter.View, CommandEventBus> {
    public interface View extends WidgetView, HasLocation {
        HasClickHandlers getButton();
        HasValue<String> getName();
    }
    
    @Inject
    protected HelloPresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);
        
        view.getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.changeLocation(view.getLocation(), view.getName().getValue());
                eventBus.fire(new HelloCommand(view.getName().getValue()));
            }});
    }

}
