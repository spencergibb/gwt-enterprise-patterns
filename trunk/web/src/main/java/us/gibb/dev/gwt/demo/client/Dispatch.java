package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEvent;
import us.gibb.dev.gwt.command.CommandEventBus;

import com.google.inject.Inject;

public class Dispatch {

    @Inject
    public Dispatch(final CommandEventBus eventBus) {
        eventBus.add(new CommandEvent.Handler<CommandA>(CommandA.class){
            public void handle(CommandEvent<CommandA> event) {
                eventBus.fire(new ResultA());
            }});
    }
}
