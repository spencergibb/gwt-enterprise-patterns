package us.gibb.dev.gwt.demo.client;

import java.util.ArrayList;
import java.util.List;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandEvent;
import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.DispatchAsync;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.event.Event;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class Dispatch {

    private CommandEventBus eventBus;
    private DispatchAsync dispatch;
    private List<Class<? extends Command<?>>> commands;

    @Inject
    public Dispatch(CommandEventBus eventBus, DispatchAsync dispatch) {
        this.eventBus = eventBus;
        this.dispatch = dispatch;
        commands = new ArrayList<Class<? extends Command<?>>>();
        commands.add(HelloCommand.class);
  
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        for (Class<? extends Command<?>> commandClass : commands) {
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
