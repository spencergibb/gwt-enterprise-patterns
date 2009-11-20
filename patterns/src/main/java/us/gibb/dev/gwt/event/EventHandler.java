package us.gibb.dev.gwt.event;

@SuppressWarnings("unchecked")
public abstract class EventHandler<E extends Event> implements com.google.gwt.event.shared.EventHandler {

	public abstract void handle(E event);
	public abstract Class<?> getTypeClass();
}
