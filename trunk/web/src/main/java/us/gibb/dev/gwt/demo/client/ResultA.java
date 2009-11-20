package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.EventResult;
import us.gibb.dev.gwt.event.EventHandler;

public class ResultA extends EventResult<String, ResultA.Handler> {
    private static final long serialVersionUID = -5277167904350114991L;

    public static abstract class Handler extends EventHandler<CommandA> {}

    public static final Type<Handler> TYPE = new Type<Handler>();

    public Type<Handler> getAssociatedType() {
        return TYPE;
    }
    

}
