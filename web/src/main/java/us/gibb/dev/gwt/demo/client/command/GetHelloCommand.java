package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Command;

public class GetHelloCommand implements Command<GetHelloResult> {
    private static final long serialVersionUID = 6440864902721536666L;
    private String name;

    GetHelloCommand() {
    }
    
    public GetHelloCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
