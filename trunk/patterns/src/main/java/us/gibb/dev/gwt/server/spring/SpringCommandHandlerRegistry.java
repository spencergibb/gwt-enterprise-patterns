package us.gibb.dev.gwt.server.spring;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import us.gibb.dev.gwt.server.command.handler.CommandHandler;
import us.gibb.dev.gwt.server.command.handler.DefaultCommandHandlerRegistry;

public class SpringCommandHandlerRegistry extends DefaultCommandHandlerRegistry implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        Map<?, CommandHandler<?, ?>> handlers = applicationContext.getBeansOfType(CommandHandler.class);
        for (CommandHandler<?, ?> handler : handlers.values()) {
            add(handler);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
