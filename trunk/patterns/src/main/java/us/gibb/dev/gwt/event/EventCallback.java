package us.gibb.dev.gwt.event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EventCallback<T> implements AsyncCallback<T> {

	private EventBus eventBus;

	public EventCallback(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void onSuccess(T result) {
		Event<T, ?> event = createEvent();
		event.setData(result);
		eventBus.fire(event);
	}

	public void onFailure(Throwable t) {
		Event<T, ?> event = createEvent();
		event.setThrowable(t);
		eventBus.fire(event);
	}
	
	public abstract Event<T, ?> createEvent();

}
