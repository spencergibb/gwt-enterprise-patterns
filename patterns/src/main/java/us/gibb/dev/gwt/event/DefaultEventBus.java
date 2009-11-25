package us.gibb.dev.gwt.event;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;

public class DefaultEventBus extends HandlerManager implements EventBus {
    
    Map<Object, EventType<?>> typeRegistry = new HashMap<Object, EventType<?>>();

    private class EventType<H extends com.google.gwt.event.shared.EventHandler> extends GwtEvent.Type<H> {
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
            return (GwtEvent.Type<EventHandler<?>>) typeRegistry.get(event.getTypeObject());
        }
    }
    
    public DefaultEventBus() {
        super(null);
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                fire(Location.fromString(event.getValue()));
            }});
    }
    
    public <E extends Event<T, H>, H extends EventHandler<E>, T> HandlerRegistration add(EventHandler<E> handler) {
        EventType<EventHandler<E>> type = getType(handler.getTypeObject());
        if (type == null) {
            type = new EventType<EventHandler<E>>();
            typeRegistry.put(handler.getTypeObject(), type);
        }
        return addHandler(type, handler);
    }
    
    public void fire(Event<?, ?> event) {
        super.fireEvent(new EventWrapper(event));
    }
    
    public boolean isHandled(Class<?> typeClass) {
        EventType<?> type = getType(typeClass);
        if (type != null) {
            return super.isEventHandled(type);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    protected <E extends Event> EventType<EventHandler<E>> getType(Object typeObject) {
        return (EventType<EventHandler<E>>) typeRegistry.get(typeObject);
    }
    
    public void changeLocation(String location, String... params) {
        History.newItem(new Location(location, params).toString(), true);
    }
    
    public Location currentLocation() {
        String token = History.getToken();
        if (token != null && !token.trim().isEmpty()) {
            return Location.fromString(token);
        }
        return null;
    }
    
    public Location currentLocation(String requiredLocation) {
        Location currentLocation = currentLocation();
        if (currentLocation != null && currentLocation.getValue().equals(requiredLocation)) {
            return currentLocation;
        }
        return null;
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
