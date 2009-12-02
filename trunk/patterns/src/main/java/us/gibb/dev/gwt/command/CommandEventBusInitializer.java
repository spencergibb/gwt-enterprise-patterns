package us.gibb.dev.gwt.command;

import java.util.Set;

import us.gibb.dev.gwt.event.Event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CommandEventBusInitializer {

    @SuppressWarnings("unchecked")
    public static void initEventBus(final CommandEventBus eventBus, final DispatchAsync dispatch, Set<Class<? extends Command<?>>> classes) {
        for (Class<? extends Command<?>> commandClass : classes) {
            eventBus.add(new CommandEvent.Handler(commandClass){
                @Override
                public void handle(Event event) {
                    if (!(event.getValue() instanceof Command)) {
                       eventBus.failure("Unable to handle command event of type: "+event.getValue().getClass());
                    }
                    final Command command = (Command) event.getValue();
                    dispatch.execute(command, new AsyncCallback<Result>() {
                        public void onFailure(Throwable t) {
                            eventBus.failure(t);
                        }
                        public void onSuccess(Result result) {
                            eventBus.fire(command, result);
                        }
                    });
                }});
        }
    }

}
