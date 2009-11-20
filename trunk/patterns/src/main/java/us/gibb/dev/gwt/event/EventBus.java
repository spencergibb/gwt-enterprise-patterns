package us.gibb.dev.gwt.event;

import com.google.gwt.event.shared.GwtEvent.Type;

public interface EventBus {

	public <H extends EventHandler<?>> void addHandler(Type<H> type, H handler);

	public void fire(Event<?, ?> event);
	
}
