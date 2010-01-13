package us.gibb.dev.gwt.server.spring;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import us.gibb.dev.gwt.command.Dispatch;
import us.gibb.dev.gwt.server.command.handler.CommandHandlerRegistry;
import us.gibb.dev.gwt.server.command.handler.DefaultDispatch;

public class DispatchFactory extends AbstractFactoryBean {
    
    private CommandHandlerRegistry registry;
    
    public void setRegistry(CommandHandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected Object createInstance() throws Exception {
        return new DefaultDispatch(registry);
    }

    @Override
    public Class<?> getObjectType() {
        return Dispatch.class;
    }

}
