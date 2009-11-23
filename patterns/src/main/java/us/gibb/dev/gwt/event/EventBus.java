package us.gibb.dev.gwt.event;

import com.google.gwt.event.shared.HandlerRegistration;


public interface EventBus {

    public <E extends Event<T, H>, H extends EventHandler<E>, T> HandlerRegistration add(EventHandler<E> handler);
    public void fire(Event<?, ?> event);
    public boolean isHandled(Class<?> typeClass);
    public void failure(String msg);
    public void failure(Throwable t);
    public void failure(String msg, Throwable t);

}
