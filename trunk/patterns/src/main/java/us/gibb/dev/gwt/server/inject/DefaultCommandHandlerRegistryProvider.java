package us.gibb.dev.gwt.server.inject;

import java.util.Set;

import us.gibb.dev.gwt.server.CommandHandler;
import us.gibb.dev.gwt.server.DefaultCommandHandlerRegistry;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class DefaultCommandHandlerRegistryProvider implements Provider<DefaultCommandHandlerRegistry> {

    private Set<Class<?>> classes;
    private Injector injector;

    @Inject
    public DefaultCommandHandlerRegistryProvider(Injector injector, @Named("command.handler.classes") Set<Class<?>> classes) {
        this.injector = injector;
        this.classes = classes;
    }
    
    @Override
    public DefaultCommandHandlerRegistry get() {
        DefaultCommandHandlerRegistry handlerRegistry = new DefaultCommandHandlerRegistry();
        try {
            for (Class<?> handlerClass : classes) {
                handlerRegistry.add((CommandHandler<?, ?>) injector.getInstance(handlerClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handlerRegistry;
    }

}
