package us.gibb.dev.gwt.view;

import us.gibb.dev.gwt.event.EventBus;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractWidgetView<EB extends EventBus> extends Composite implements WidgetView {

	protected EB eventBus;
    
	public AbstractWidgetView(EB eventBus) {
		this.eventBus = eventBus;
	}

	public Widget getImpl() {
	    return this;
	}
}
