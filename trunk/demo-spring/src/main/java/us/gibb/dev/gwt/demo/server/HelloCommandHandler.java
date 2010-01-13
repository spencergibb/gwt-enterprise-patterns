package us.gibb.dev.gwt.demo.server;

import us.gibb.dev.gwt.command.results.StringResult;
import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.server.command.handler.CommandHandler;
import us.gibb.dev.gwt.server.command.handler.Context;

@SuppressWarnings("unchecked")
public interface HelloCommandHandler extends CommandHandler {
    public StringResult sayHello(SayHelloCommand command, Context context);
    public HelloResult getHello(GetHelloCommand command, Context context);
}
