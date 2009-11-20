package us.gibb.dev.gwt.client.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;

public class EventBus {

	private HandlerManager bus;

	@Inject
	public EventBus(HandlerManager bus) {
		this.bus = bus;
	}

	public <H extends EventHandler<?>> void addHandler(Type<H> type, H handler) {
		bus.addHandler(type, handler);
	}

	public void fire(Event<?, ?> event) {
		bus.fireEvent(event);
	}
	
}
