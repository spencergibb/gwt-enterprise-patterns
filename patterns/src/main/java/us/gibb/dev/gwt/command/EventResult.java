package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.event.EventHandler;

@SuppressWarnings( { "unchecked", "serial" })
public abstract class EventResult<T, H extends EventHandler> extends Event<T, H> implements Result {
 
    public EventResult() {
    }

    public EventResult(T data) {
        super(data);
    }

}
