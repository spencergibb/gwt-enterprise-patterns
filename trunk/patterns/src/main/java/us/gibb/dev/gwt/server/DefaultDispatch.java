package us.gibb.dev.gwt.server;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Dispatch;
import us.gibb.dev.gwt.command.Result;

public class DefaultDispatch implements Dispatch {
    
    private CommandHandlerRegistry handlerRegistry;

    public DefaultDispatch(CommandHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }
    
    @Override
    public <C extends Command<R>, R extends Result> R execute(C command) throws CommandException {
        CommandHandler<C, R> handler = handlerRegistry.findHandler(command);
        return handler.execute(command);
    }

}
