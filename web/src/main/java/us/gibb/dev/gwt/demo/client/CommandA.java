package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.EventCommand;
import us.gibb.dev.gwt.event.EventHandler;

public class CommandA extends EventCommand<String, ResultA, CommandA.Handler> {
    private static final long serialVersionUID = 6440864902721536666L;

    public static abstract class Handler extends EventHandler<CommandA> {}

    public static final Type<Handler> TYPE = new Type<Handler>();
    
    public CommandA() {
        super("a");
    }

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

}
