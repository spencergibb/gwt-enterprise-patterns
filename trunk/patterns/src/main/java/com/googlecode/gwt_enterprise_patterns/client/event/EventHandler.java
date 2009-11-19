package com.googlecode.gwt_enterprise_patterns.client.event;

@SuppressWarnings("unchecked")
public abstract class EventHandler<E extends Event> implements com.google.gwt.event.shared.EventHandler {

	void handle(E event) {
		if (event.getThrowable() != null) {
			failure(event);
		} else {
			success(event);
		}
	}

	public void failure(E event) {
	}
	
	public abstract void success(E event);
}
