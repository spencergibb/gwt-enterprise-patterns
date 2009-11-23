package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.event.EventHandler;

public class CommandEvent<C extends Command<?>> extends Event<C, CommandEvent.Handler<C>> {
    private static final long serialVersionUID = -710409787744632889L;

    public abstract static class Handler<C extends Command<?>> extends EventHandler<CommandEvent<C>> {
        private Class<? extends Command<?>> commandClass;

        public Handler(Class<C> commandClass) {
            this.commandClass = commandClass;
        }

        @Override
        public Object getTypeObject() {
            return commandClass;
        }
        
    }

    public CommandEvent(C command) {
        super(command);
    }
    
    public C getCommand() {
        return getValue();
    }
    
    @Override
    public Object getTypeObject() {
        return getCommand().getClass();
    }

}
