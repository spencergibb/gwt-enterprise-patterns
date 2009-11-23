package us.gibb.dev.gwt.event;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class DefaultEventBus extends HandlerManager implements EventBus {
    
    Map<Class<?>, ClassEventType<?>> typeRegistry = new HashMap<Class<?>, ClassEventType<?>>();

    private class ClassEventType<H extends com.google.gwt.event.shared.EventHandler> extends GwtEvent.Type<H> {
    }
    
    private class EventWrapper extends GwtEvent<EventHandler<?>> {
        private Event<?, EventHandler<?>> event;
        
        @SuppressWarnings("unchecked")
        public EventWrapper(Event<?, ?> event) {
            this.event = (Event<?, EventHandler<?>>) event;
        }

        protected void dispatch(EventHandler<?> handler) {
            event.dispatch(handler);
        }

        @SuppressWarnings("unchecked")
        public GwtEvent.Type<EventHandler<?>> getAssociatedType() {
            return (GwtEvent.Type<EventHandler<?>>) typeRegistry.get(event.getTypeClass());
        }
    }
    
    public DefaultEventBus() {
        super(null);
    }
    
    public <E extends Event<T, H>, H extends EventHandler<E>, T> HandlerRegistration add(EventHandler<E> handler) {
        ClassEventType<EventHandler<E>> type = getType(handler.getTypeClass());
        if (type == null) {
            type = new ClassEventType<EventHandler<E>>();
            typeRegistry.put(handler.getTypeClass(), type);
        }
        return addHandler(type, handler);
    }
    
    public void fire(Event<?, ?> event) {
        super.fireEvent(new EventWrapper(event));
    }
    
    public boolean isHandled(Class<?> typeClass) {
        ClassEventType<?> type = getType(typeClass);
        if (type != null) {
            return super.isEventHandled(type);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    protected <E extends Event> ClassEventType<EventHandler<E>> getType(Class<?> typeClass) {
        return (ClassEventType<EventHandler<E>>) typeRegistry.get(typeClass);
    }

    @Override
    public void failure(String msg) {
        fire(new FailureEvent(msg));
    }

    @Override
    public void failure(Throwable t) {
        fire(new FailureEvent(t));
    }

    @Override
    public void failure(String msg, Throwable t) {
        fire(new FailureEvent(msg, t));
    }

}
