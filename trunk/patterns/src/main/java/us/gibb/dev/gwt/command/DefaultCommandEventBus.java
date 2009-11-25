package us.gibb.dev.gwt.command;

import java.util.Set;

import us.gibb.dev.gwt.event.DefaultEventBus;
import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.location.LocationManager;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;

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

    public <R extends Result> void fire(R result) {
        super.fire(new ResultEvent<R>(result));
    }

    @SuppressWarnings("unchecked")
    public static void initEventBus(final CommandEventBus eventBus, final DispatchAsync dispatch, Set<Class<? extends Command<?>>> classes) {
        for (Class<? extends Command<?>> commandClass : classes) {
            eventBus.add(new CommandEvent.Handler(commandClass){
                @Override
                public void handle(Event event) {
                    if (!(event.getValue() instanceof Command)) {
                       eventBus.failure("Unable to handle command event of type: "+event.getValue().getClass());
                    }
                    Command command = (Command) event.getValue();
                    dispatch.execute(command, new AsyncCallback<Result>() {
                        public void onFailure(Throwable t) {
                            eventBus.failure(t);
                        }
                        public void onSuccess(Result result) {
                            eventBus.fire(result);
                        }
                    });
                }});
        }
    }
}
