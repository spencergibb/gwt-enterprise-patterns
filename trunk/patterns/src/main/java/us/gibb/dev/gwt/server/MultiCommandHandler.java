package us.gibb.dev.gwt.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.server.inject.DispatchIgnore;

@DispatchIgnore
@SuppressWarnings("unchecked")
public abstract class MultiCommandHandler extends CommandHandler {
    
    Map<Class<? extends Command<?>>, Method> commandMethods = new HashMap<Class<? extends Command<?>>, Method>();
    
    protected MultiCommandHandler() {
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            boolean returnsResult = Result.class.isAssignableFrom(method.getReturnType());
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes != null && parameterTypes.length == 1) {
                boolean takesCommand = Command.class.isAssignableFrom(parameterTypes[0]);
                boolean isChildMethod = method.getDeclaringClass() != MultiCommandHandler.class && method.getDeclaringClass() != CommandHandler.class;
                if (takesCommand && returnsResult && isChildMethod) {
                    if (commandMethods.containsKey(parameterTypes[0])) {
                        //TODO move to log
                        System.err.println(this.getClass().getName() +" already contains a method for handling command: "+
                                parameterTypes[0]+", ignoring");
                    } else {
                        commandMethods.put((Class<? extends Command<?>>) parameterTypes[0], method);
                    }
                }
            }
            
        }
    }

    @Override
    public Result execute(Command command) throws CommandException {
        Method method = commandMethods.get(command.getClass());
        if (method == null) {
            throw new CommandException("Could not find method in "+getClass()+" to handle command: "+command.getClass());
        }

        try {
            return (Result) method.invoke(this, command);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause instanceof CommandException) {
                throw (CommandException)cause;
            }
            throw new CommandException("Error executing "+method, e);
        } catch (Exception e) {
            throw new CommandException("Error executing "+method, e);
        }
    }

    @Override
    public Map getCommandMapping() {
        Map<Class<? extends Command<?>>, CommandHandler<?, ?>> mapping = new HashMap<Class<? extends Command<?>>, CommandHandler<?,?>>();
        for (Class<? extends Command<?>> commandClazz : commandMethods.keySet()) {
            mapping.put(commandClazz, this);
        }
        return mapping;
    }
}
