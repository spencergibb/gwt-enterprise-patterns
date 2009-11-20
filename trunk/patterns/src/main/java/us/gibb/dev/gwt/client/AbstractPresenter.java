package us.gibb.dev.gwt.client;

import us.gibb.dev.gwt.client.event.EventBus;

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
