package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.inject.Inject;

public class HelloPresenter extends AbstractPresenter<HelloPresenter.View, CommandEventBus> {
    public interface View extends WidgetView {
        HasClickHandlers getButton();
    }
    
    @Inject
    protected HelloPresenter(final CommandEventBus eventBus, View view) {
        super(eventBus, view);
        
        view.getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fire(new CommandA());
            }});
    }

}
