package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.DefaultCommandEventBus;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class InjectorConfig extends AbstractGinModule {

	@Override
	protected void configure() {
	    bind(Dispatch.class).asEagerSingleton();
		bind(HelloPresenter.View.class).to(HelloViewImpl.class);
		bind(CommandEventBus.class).to(DefaultCommandEventBus.class).in(Singleton.class);
		bindConstant().annotatedWith(Names.named("refresh.millis")).to(20000);
	}

}
