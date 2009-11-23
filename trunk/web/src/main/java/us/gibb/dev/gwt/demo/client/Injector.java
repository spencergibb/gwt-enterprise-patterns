package us.gibb.dev.gwt.demo.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(InjectorConfig.class)
public interface Injector extends Ginjector {
    HelloPresenter getHelloPresenter();
}
