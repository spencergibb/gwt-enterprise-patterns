package us.gibb.dev.gwt.demo.server;

import us.gibb.dev.gwt.command.Dispatch;
import us.gibb.dev.gwt.server.AppengineLoginHandler;
import us.gibb.dev.gwt.server.CommandHandlerRegistry;
import us.gibb.dev.gwt.server.inject.DefaultCommandHandlerRegistryProvider;
import us.gibb.dev.gwt.server.inject.DefaultDispatchProvider;
import us.gibb.dev.gwt.server.inject.DispatchModule;
import us.gibb.dev.gwt.server.inject.GuiceRemoteServiceServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class DemoServletContextListener extends GuiceServletContextListener {

    protected Injector getInjector() {
        return Guice.createInjector(new DispatchModule() {
            protected void configureDispatch() {
                install(new ServletModule() {
                    protected void configureServlets() {
                        serve("/us.gibb.dev.gwt.demo.Application/gwt.rpc").with(GuiceRemoteServiceServlet.class);
                    }});
                scan(HelloCommandHandler.class.getPackage());
                addCommandHandler(AppengineLoginHandler.class);
                
                bind(CommandHandlerRegistry.class).toProvider(DefaultCommandHandlerRegistryProvider.class).in(Singleton.class);

                bind(Dispatch.class).toProvider(DefaultDispatchProvider.class).in(Singleton.class);
                
                bindEntityManagerFactoryProvider("transactions-optional");
            }});
    }
}