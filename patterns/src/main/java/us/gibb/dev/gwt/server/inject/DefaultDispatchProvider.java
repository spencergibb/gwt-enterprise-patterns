package us.gibb.dev.gwt.server.inject;

import us.gibb.dev.gwt.server.command.handler.CommandHandlerRegistry;
import us.gibb.dev.gwt.server.command.handler.DefaultDispatch;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DefaultDispatchProvider implements Provider<DefaultDispatch>{

    private CommandHandlerRegistry handlerRegistry;

    @Inject
    public DefaultDispatchProvider(CommandHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }
    
    @Override
    public DefaultDispatch get() {
        return new DefaultDispatch(handlerRegistry);
    }

}
