package us.gibb.dev.gwt.command;


import us.gibb.dev.gwt.event.DefaultEventBus;
import us.gibb.dev.gwt.location.LocationManager;

import com.google.gwt.event.shared.HandlerRegistration;

public class DefaultCommandEventBus extends DefaultEventBus implements CommandEventBus {

    public DefaultCommandEventBus(LocationManager locationManager) {
        super(locationManager);
    }

    public <C extends Command<?>> HandlerRegistration add(CommandEvent.Handler<C> handler) {
        return super.add(handler);
    }

    public <C extends Command<?>> void fire(C command) {
        super.fire(new CommandEvent<C>(command));
    }
    
    public <R extends Result> HandlerRegistration add(ResultEvent.Handler<R> handler) {
        return super.add(handler);
    }

    @SuppressWarnings("unchecked")
    public <C extends Command<R>, R extends Result> void fire(C command, R result) {
        super.fire(new ResultEvent<R>((Class<? extends Command<R>>) command.getClass(), result));
    }
}
