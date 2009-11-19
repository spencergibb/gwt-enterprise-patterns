package com.googlecode.gwt_enterprise_patterns.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt_enterprise_patterns.client.event.EventBus;

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
