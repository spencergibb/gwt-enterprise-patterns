package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(InjectorConfig.class)
public interface Injector extends Ginjector {
	CommandEventBus getEventBus();
	Dispatch getDispatch();
	
    HelloPresenter getHelloPresenter();
    HelloPresenter.View getHelloView();

}
