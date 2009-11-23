package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.Command;

public class HelloCommand implements Command<HelloResult> {
    private static final long serialVersionUID = 6440864902721536666L;
    private String name;

    HelloCommand() {
    }
    
    public HelloCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
