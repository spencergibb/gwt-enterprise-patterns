package us.gibb.dev.gwt.command.inject;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandEvent;
import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.DispatchAsync;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.event.Event;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * Bootstraps the CommandEventBus to handle sending command to the serverside Dispatch classes
 * and firing Results
 * 
 * TODO: need to inject a client side error handler
 *
 */
public class Initializer {

    private DispatchAsync dispatch;
    private CommandEventBus eventBus;
    private CommandClasses classes;
    
    @Inject
    public Initializer(CommandEventBus eventBus, DispatchAsync dispatch, CommandClasses classes) {
        this.eventBus = eventBus;
        this.dispatch = dispatch;
        this.classes = classes;
        
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        for (Class<? extends Command<?>> commandClass : classes.getCommands()) {
            eventBus.add(new CommandEvent.Handler(commandClass){
                @Override
                public void handle(Event event) {
                    if (!(event.getData() instanceof Command)) {
                        Window.alert("Unable to handle command event of type: "+event.getData().getClass());
                    }
                    Command command = (Command) event.getData();
                    dispatch.execute(command, new AsyncCallback<Result>() {
                        public void onFailure(Throwable t) {
                            Window.alert(t.getMessage());
                        }
                        public void onSuccess(Result result) {
                            eventBus.fire(result);
                        }
                    });
                }});
        }
    }

}
