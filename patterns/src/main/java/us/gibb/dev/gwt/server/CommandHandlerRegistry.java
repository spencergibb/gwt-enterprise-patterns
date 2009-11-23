package us.gibb.dev.gwt.server;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.Result;

public interface CommandHandlerRegistry {
    public <C extends Command<R>, R extends Result> CommandHandler<C, R> findHandler(C command);

    public void clear();
    
    public void add(CommandHandler<?, ?> handler) ;
}
