package us.gibb.dev.gwt.command;

import com.google.gwt.event.shared.HandlerRegistration;

import us.gibb.dev.gwt.event.DefaultEventBus;

public class DefaultCommandEventBus extends DefaultEventBus implements CommandEventBus {

    public <C extends Command<?>> HandlerRegistration add(CommandEvent.Handler<C> handler) {
        return super.add(handler);
    }

    public <C extends Command<?>> void fire(C command) {
        super.fire(new CommandEvent<C>(command));
    }
    
    public <R extends Result> HandlerRegistration add(ResultEvent.Handler<R> handler) {
        return super.add(handler);
    }

    public <R extends Result> void fire(R result) {
        super.fire(new ResultEvent<R>(result));
    }
}
