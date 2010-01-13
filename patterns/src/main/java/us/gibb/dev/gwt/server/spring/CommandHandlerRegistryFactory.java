package us.gibb.dev.gwt.server.spring;

import java.util.List;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import us.gibb.dev.gwt.server.command.handler.CommandHandler;
import us.gibb.dev.gwt.server.command.handler.CommandHandlerRegistry;
import us.gibb.dev.gwt.server.command.handler.DefaultCommandHandlerRegistry;

public class CommandHandlerRegistryFactory extends AbstractFactoryBean {
    
    private List<CommandHandler<?, ?>> handlers;
    
    public void setHandlers(List<CommandHandler<?, ?>> handlers) {
        this.handlers = handlers;
    }

    @Override
    protected Object createInstance() throws Exception {
        DefaultCommandHandlerRegistry registry = new DefaultCommandHandlerRegistry();
        for (CommandHandler<?, ?> handler : handlers) {
            registry.add(handler);
        }
        return registry;
    }

    @Override
    public Class<?> getObjectType() {
        return CommandHandlerRegistry.class;
    }

}
