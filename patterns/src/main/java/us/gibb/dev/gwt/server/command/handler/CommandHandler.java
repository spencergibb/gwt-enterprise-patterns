package us.gibb.dev.gwt.server.command.handler;

import java.util.Map;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;

public interface CommandHandler<C extends Command<R>, R extends Result> {
    public R execute(C command, Context context) throws CommandException;
    public Map<Class<? extends Command<?>>, CommandHandler<?, ?>> getCommandMapping();
}
