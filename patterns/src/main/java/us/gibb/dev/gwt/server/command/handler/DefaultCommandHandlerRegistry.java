package us.gibb.dev.gwt.server.command.handler;

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
        Map<Class<? extends Command<?>>, CommandHandler<?, ?>> commandMapping = handler.getCommandMapping();
        for (Class<? extends Command<?>> commandClass : commandMapping.keySet()) {
            if (registry.containsKey(commandClass)) {
                //TODO move to log
                System.err.println(this.getClass() +" already contains a CommandHandler for command: "+commandClass+", ignoring");
            } else {
                registry.put(commandClass, commandMapping.get(commandClass));
            }
        }
    }

}
