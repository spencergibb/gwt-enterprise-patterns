package us.gibb.dev.gwt.command.inject;

import java.util.HashSet;
import java.util.Set;

import us.gibb.dev.gwt.command.Command;

/**
 * Since gin doesn't support toInstance bindings this class is used to hold that list.
 * The client needs to subclass and implement addCommands()
 * 
 * public DemoCommandClasses extends CommandClasses {
 *    protected void addCommands() {
 *       add(MyCommand.class);
 *       add(AnotherCommand.class);
 *    }
 * }
 */
public abstract class CommandClasses {
    protected Set<Class<? extends Command<?>>> commands = new HashSet<Class<? extends Command<?>>>();

    public CommandClasses() {
    }
    
    public void add(Class<? extends Command<?>> command) {
        commands.add(command);
    }
    
    public Set<Class<? extends Command<?>>> getCommands() {
        addCommands();
        return commands;
    }

    protected abstract void addCommands();

}
