package us.gibb.dev.gwt.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;

public abstract class AbstractCommandHandler<C extends Command<R>, R extends Result> implements CommandHandler<C, R> {
    
    public abstract R execute(C command, Context context) throws CommandException;
    
    @SuppressWarnings("unchecked")
    protected Class<? extends Command<?>> getCommandClass() {
        ParameterizedType genericSuperclass = (ParameterizedType)getClass().getGenericSuperclass();
        Type[] typeArgs = genericSuperclass.getActualTypeArguments();
        Class<? extends Command<?>> clazz = (Class<? extends Command<?>>)typeArgs[0];
        return clazz;
    }
    
    public Map<Class<? extends Command<?>>, CommandHandler<?, ?>> getCommandMapping() {
        Map<Class<? extends Command<?>>, CommandHandler<?, ?>> mapping = new HashMap<Class<? extends Command<?>>, CommandHandler<?,?>>();
        mapping.put(getCommandClass(), this);
        return mapping;
    }
}
