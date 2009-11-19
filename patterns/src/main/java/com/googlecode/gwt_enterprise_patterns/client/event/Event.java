package com.googlecode.gwt_enterprise_patterns.client.event;

import com.google.gwt.event.shared.GwtEvent;

@SuppressWarnings("unchecked")
public abstract class Event<T, H extends EventHandler> extends GwtEvent<H> {

	private T data;
	
	private Throwable throwable;
	
	public Event() {
	}
	
	public Event(T data) {
		this.data = data;
	}
	
	public Event(Throwable t) {
		this.throwable = t;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	protected void dispatch(H handler) {
		handler.handle(this);
	};
}
