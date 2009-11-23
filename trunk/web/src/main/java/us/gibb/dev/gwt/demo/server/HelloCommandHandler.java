package us.gibb.dev.gwt.demo.server;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.demo.client.HelloCommand;
import us.gibb.dev.gwt.demo.client.HelloResult;
import us.gibb.dev.gwt.server.CommandHandler;

public class HelloCommandHandler extends CommandHandler<HelloCommand, HelloResult> {

    @Override
    public HelloResult execute(HelloCommand command) throws CommandException {
        return new HelloResult("Hello "+command.getName());
    }

}
