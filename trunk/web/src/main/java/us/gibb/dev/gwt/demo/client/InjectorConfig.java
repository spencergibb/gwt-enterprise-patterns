package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.inject.ClientDispatchModule;
import us.gibb.dev.gwt.command.inject.CommandClasses;

import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class InjectorConfig extends ClientDispatchModule {

    @Override
    protected void configureClient() {
        bind(HelloPresenter.class).in(Singleton.class);
		bind(HelloPresenter.View.class).to(HelloViewImpl.class).in(Singleton.class);
        bind(GoodbyePresenter.class).in(Singleton.class);
        bind(GoodbyePresenter.View.class).to(GoodbyeViewImpl.class).in(Singleton.class);
        
		bindDefaultCommandEventBus();
		bind(CommandClasses.class).to(DemoCommandClasses.class);
        
		bindConstant().annotatedWith(Names.named("refresh.millis")).to(20000);
	}

}
