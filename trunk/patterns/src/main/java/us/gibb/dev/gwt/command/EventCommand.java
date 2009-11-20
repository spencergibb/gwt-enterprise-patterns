package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.event.EventHandler;

@SuppressWarnings( { "serial", "unchecked" })
public abstract class EventCommand<T, R extends Result, H extends EventHandler> extends Event<T, H> implements Command<R> {
 
    public EventCommand() {
    }

    public EventCommand(T data) {
        super(data);
    }

}
