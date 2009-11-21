package us.gibb.dev.gwt.server;

import java.util.HashMap;
import java.util.Map;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.Result;

public class DefaultCommandHandlerRegistry implements CommandHandlerRegistry {
    
    protected Map<Class<? extends Command<?>>, CommandHandler<?, ?>> registry;
    
    public DefaultCommandHandlerRegistry() {
        registry = new HashMap<Class<? extends Command<?>>, CommandHandler<?,?>>();
    }

    @Override
    public void clear() {
        registry.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C extends Command<R>, R extends Result> CommandHandler<C, R> findHandler(C command) {
        return (CommandHandler<C, R>) registry.get(command.getClass());
    }
    
    public void add(CommandHandler<?, ?> handler) {
        registry.put(handler.getCommandClass(), handler);
    }

}
