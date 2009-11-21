package us.gibb.dev.gwt.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;

public abstract class CommandHandler<C extends Command<R>, R extends Result> {
    public abstract R execute(C command) throws CommandException;

    @SuppressWarnings("unchecked")
    public Class<? extends Command<?>> getCommandClass() {
        ParameterizedType genericSuperclass = (ParameterizedType)getClass().getGenericSuperclass();
        Type[] typeArgs = genericSuperclass.getActualTypeArguments();
        Class<? extends Command<?>> clazz = (Class<? extends Command<?>>)typeArgs[0];
        return clazz;
    }
}
