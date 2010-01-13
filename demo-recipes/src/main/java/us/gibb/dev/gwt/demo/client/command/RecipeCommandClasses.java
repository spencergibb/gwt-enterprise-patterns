package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.inject.CommandClasses;

public class RecipeCommandClasses extends CommandClasses {
    protected void addCommands() {
        add(GetRecipesCommand.class);
        add(GetRecipeCommand.class);
        add(SaveRecipeCommand.class);
    }
}
