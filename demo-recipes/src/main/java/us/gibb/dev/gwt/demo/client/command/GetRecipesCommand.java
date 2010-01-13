package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Command;

public class GetRecipesCommand implements Command<RecipesResult> {
    private static final long serialVersionUID = 6440864902721536666L;
    private String name;

    public GetRecipesCommand() {
    }
    
    public GetRecipesCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
