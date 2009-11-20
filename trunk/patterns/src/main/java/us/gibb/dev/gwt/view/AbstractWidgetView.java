package us.gibb.dev.gwt.view;

import us.gibb.dev.gwt.event.EventBus;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractWidgetView extends Composite implements WidgetView {

	protected EventBus eventBus;
    
	public AbstractWidgetView(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public Widget getView() {
	    return this;
	}
}
