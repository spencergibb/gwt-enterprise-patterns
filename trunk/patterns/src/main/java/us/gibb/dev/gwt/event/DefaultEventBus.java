package us.gibb.dev.gwt.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;

public class DefaultEventBus extends HandlerManager implements EventBus {

    public DefaultEventBus() {
        super(null);
    }

    public <H extends EventHandler<?>> void addHandler(Type<H> type, H handler) {
        super.addHandler(type, handler);
    }

    public void fire(Event<?, ?> event) {
        super.fireEvent(event);
    }
}
