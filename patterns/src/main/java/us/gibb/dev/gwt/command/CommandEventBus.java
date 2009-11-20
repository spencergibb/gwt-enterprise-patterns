package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.EventBus;

import com.google.gwt.event.shared.HandlerRegistration;

public interface CommandEventBus extends EventBus {

    public <C extends Command<?>> HandlerRegistration add(CommandEvent.Handler<C> handler);
    public <C extends Command<?>> void fire(C command);

    public <R extends Result> HandlerRegistration add(ResultEvent.Handler<R> handler);
    public <R extends Result> void fire(R result);
    
}
