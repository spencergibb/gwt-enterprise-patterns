package us.gibb.dev.gwt.server.inject;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.not;
import static com.google.inject.matcher.Matchers.subclassesOf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import us.gibb.dev.gwt.command.Dispatch;
import us.gibb.dev.gwt.server.command.handler.CommandHandler;
import us.gibb.dev.gwt.server.command.handler.CommandHandlerRegistry;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class DispatchModule extends AbstractModule {
    private ArrayList<Package> packages;
    private Set<Class<?>> classes;
    
    @Override
    protected void configure() {
        requireBinding(CommandHandlerRegistry.class);
        requireBinding(Dispatch.class);
        
        packages = new ArrayList<Package>();
        classes = new HashSet<Class<?>>();
        
        configureDispatch();

        for (Package pkg : packages) {
          classes.addAll(Classes.matching(subclassesOf(CommandHandler.class).and(not(annotatedWith(DispatchIgnore.class)))).in(pkg));
        }
        classes.remove(CommandHandler.class);
        
        bind(new TypeLiteral<Set<Class<?>>>() {})
            .annotatedWith(Names.named("command.handler.classes"))
            .toInstance(classes);

        System.out.println(classes); //TODO move to log
    }
    
    protected void configureDispatch() {
        
    }
    
    protected void scan(Package pkg) {
        assert pkg != null;
        packages.add(pkg);
    }
    
    protected <CH extends CommandHandler<?, ?>> void addCommandHandler(Class<CH> clazz) {
        classes.add(clazz);
    }
    
}
