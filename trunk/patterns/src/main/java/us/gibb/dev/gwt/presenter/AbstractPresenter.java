package us.gibb.dev.gwt.presenter;

import us.gibb.dev.gwt.event.EventBus;
import us.gibb.dev.gwt.view.View;

public abstract class AbstractPresenter<V extends View<?>> implements Presenter<V> {
	private V view;
	protected EventBus eventBus;

	protected AbstractPresenter(EventBus eventBus, V view) {
		this.eventBus = eventBus;
		this.view = view;
	}

	public V getView() {
		return view;
	}
}
