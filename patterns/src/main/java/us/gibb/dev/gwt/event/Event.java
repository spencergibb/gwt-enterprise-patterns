package us.gibb.dev.gwt.event;

import com.google.gwt.event.shared.GwtEvent;

@SuppressWarnings("unchecked")
public abstract class Event<T, H extends EventHandler> extends GwtEvent<H> {

	private T data;
	
	public Event() {
	}
	
	public Event(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Implementation of abstract method in GwtEvent
	 */
	protected void dispatch(H handler) {
		handler.handle(this);
	};

}
