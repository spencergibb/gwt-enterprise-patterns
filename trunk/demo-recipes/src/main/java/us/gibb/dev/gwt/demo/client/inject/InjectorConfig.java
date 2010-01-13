package us.gibb.dev.gwt.demo.client.inject;

import us.gibb.dev.gwt.command.inject.ClientDispatchModule;
import us.gibb.dev.gwt.command.inject.CommandClasses;
import us.gibb.dev.gwt.demo.client.RecipePresenter;
import us.gibb.dev.gwt.demo.client.RecipeViewImpl;
import us.gibb.dev.gwt.demo.client.RecipesPresenter;
import us.gibb.dev.gwt.demo.client.RecipesViewImpl;
import us.gibb.dev.gwt.demo.client.command.RecipeCommandClasses;

import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class InjectorConfig extends ClientDispatchModule {

    @Override
    protected void configureClient() {
        bind(RecipesPresenter.class).in(Singleton.class);
        bind(RecipesPresenter.View.class).to(RecipesViewImpl.class).in(Singleton.class);
        bind(RecipePresenter.class).in(Singleton.class);
        bind(RecipePresenter.View.class).to(RecipeViewImpl.class).in(Singleton.class);
        
		bindDefaultCommandEventBus();
        bindDefaultLocationManager();
		bind(CommandClasses.class).to(RecipeCommandClasses.class);
        
		bindConstant().annotatedWith(Names.named("refresh.millis")).to(20000);
	}

}
