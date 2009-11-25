package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.inject.CommandClasses;
import us.gibb.dev.gwt.login.LoginCommand;

public class DemoCommandClasses extends CommandClasses {
    protected void addCommands() {
        add(HelloCommand.class);
        add(LoginCommand.class);
    }
}
