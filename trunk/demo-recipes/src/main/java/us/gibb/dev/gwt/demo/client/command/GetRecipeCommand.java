package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Command;

public class GetRecipeCommand implements Command<RecipeResult> {
    private static final long serialVersionUID = -7944106025776530524L;
    private Long id;

    public GetRecipeCommand() {
    }
    
    public GetRecipeCommand(String id) {
        this.id = new Long(id);
    }

    public Long getId() {
        return id;
    }

}
