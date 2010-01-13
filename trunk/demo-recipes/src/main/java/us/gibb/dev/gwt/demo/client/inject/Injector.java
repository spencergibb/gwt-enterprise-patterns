package us.gibb.dev.gwt.demo.client.inject;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.demo.client.RecipesPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(InjectorConfig.class)
public interface Injector extends Ginjector {
    RecipesPresenter getHelloPresenter();
    CommandEventBus getEventBus();
}
