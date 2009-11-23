package us.gibb.dev.gwt.command.inject;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.DefaultCommandEventBus;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ClientDispatchModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(Initializer.class).asEagerSingleton();
        
        configureClient();
    }

    protected void configureClient() {
    }

    protected void bindDefaultCommandEventBus() {
        bind(CommandEventBus.class).to(DefaultCommandEventBus.class).in(Singleton.class);
    }

}
