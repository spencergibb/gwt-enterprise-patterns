package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.inject.CommandClasses;

public class DemoCommandClasses extends CommandClasses {
    protected void addCommands() {
        add(SayHelloCommand.class);
        add(GetHelloCommand.class);
    }
}
