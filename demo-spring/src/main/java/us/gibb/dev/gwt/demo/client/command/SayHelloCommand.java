package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.results.StringResult;

public class SayHelloCommand implements Command<StringResult> {
    private static final long serialVersionUID = 6440864902721536666L;
    private String name;

    SayHelloCommand() {
    }
    
    public SayHelloCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
