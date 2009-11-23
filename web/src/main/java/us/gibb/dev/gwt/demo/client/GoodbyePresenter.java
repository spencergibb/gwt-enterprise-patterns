package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.event.HasLocation;
import us.gibb.dev.gwt.event.LocationChangedEvent;
import us.gibb.dev.gwt.presenter.AbstractPresenter;
import us.gibb.dev.gwt.view.WidgetView;

import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

public class GoodbyePresenter extends AbstractPresenter<GoodbyePresenter.View, CommandEventBus> {

    public interface View extends WidgetView, HasLocation {
        HasValue<String> getName();
    }
    
    @Inject
    protected GoodbyePresenter(final CommandEventBus eventBus, final View view) {
        super(eventBus, view);

        eventBus.add(new LocationChangedEvent.Handler(view.getLocation()) {
            public void handle(LocationChangedEvent event) {
                view.getName().setValue(event.getParam(0));
            }});
    }

}
