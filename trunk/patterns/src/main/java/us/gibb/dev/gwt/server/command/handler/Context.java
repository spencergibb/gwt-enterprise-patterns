package us.gibb.dev.gwt.server.command.handler;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;

public interface Context {
    public <C extends Command<R>, R extends Result> R execute(C command) throws CommandException;
}
