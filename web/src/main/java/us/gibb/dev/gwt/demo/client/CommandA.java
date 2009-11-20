package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.Command;

public class CommandA implements Command<ResultA> {
    private static final long serialVersionUID = 6440864902721536666L;

    public CommandA() {
    }

    public String getA() {
        return "a";
    }

}
