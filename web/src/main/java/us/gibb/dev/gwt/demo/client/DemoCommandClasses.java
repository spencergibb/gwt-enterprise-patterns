package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.inject.CommandClasses;

public class DemoCommandClasses extends CommandClasses {
    protected void addCommands() {
        add(HelloCommand.class);
    }
}
