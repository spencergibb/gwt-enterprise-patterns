package us.gibb.dev.gwt.server.command.handler;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Dispatch;
import us.gibb.dev.gwt.command.Result;

public class DefaultDispatch implements Dispatch, Context {
    
    private CommandHandlerRegistry handlerRegistry;

    public DefaultDispatch(CommandHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }
    
    @Override
    public <C extends Command<R>, R extends Result> R execute(C command) throws CommandException {
        CommandHandler<C, R> handler = handlerRegistry.findHandler(command);
        if (handler == null) {
            throw new CommandException("Unable to find CommandHandler for command class: "+command.getClass());
        }
        try {
            return (R) handler.execute(command, this);
        } catch (CommandException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace(); //TODO move to log
            throw new CommandException(e);
        }
    }

}
