package us.gibb.dev.gwt.client;

import us.gibb.dev.gwt.client.event.EventBus;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractView extends Composite implements WidgetView {

	protected EventBus eventBus;
    
    protected int defaultWidth;

	public AbstractView(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public Widget widget() {
		return this;
	}
	
}
