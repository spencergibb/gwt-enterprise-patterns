package us.gibb.dev.gwt.command.inject;


import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.DefaultCommandEventBus;
import us.gibb.dev.gwt.command.DispatchAsync;

import com.google.inject.Inject;

/**
 * Bootstraps the CommandEventBus to handle sending command to the serverside Dispatch classes
 * and firing Results
 * 
 * TODO: need to inject a client side error handler
 *
 */
public class Initializer {

    @Inject
    public Initializer(CommandEventBus eventBus, DispatchAsync dispatch, CommandClasses classes) {
        DefaultCommandEventBus.initEventBus(eventBus, dispatch, classes.getCommands());
    }

}
