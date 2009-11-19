package com.googlecode.gwt_enterprise_patterns.client;

import com.googlecode.gwt_enterprise_patterns.client.event.EventBus;

public abstract class AbstractPresenter {
	private WidgetView view;
	protected EventBus eventBus;

	protected AbstractPresenter(EventBus eventBus, WidgetView view) {
		this.eventBus = eventBus;
		this.view = view;
	}

	public WidgetView getView() {
		return view;
	}
}
