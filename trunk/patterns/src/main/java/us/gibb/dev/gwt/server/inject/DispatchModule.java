package us.gibb.dev.gwt.server.inject;

import static com.google.inject.matcher.Matchers.subclassesOf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import us.gibb.dev.gwt.server.CommandHandler;

import com.google.inject.AbstractModule;

public class DispatchModule extends AbstractModule {

    private ArrayList<Package> packages;

    @Override
    protected void configure() {
        packages = new ArrayList<Package>();
        
        configureDispatch();
        

        Set<Class<?>> set = new HashSet<Class<?>>();
        for (Package pkg : packages) {

          //look for any classes annotated with @At, @EmbedAs and @Export
          //set.addAll(Classes.matching(annotatedWith(TestAnnotation.class)).in(pkg));
          set.addAll(Classes.matching(subclassesOf(CommandHandler.class)).in(pkg));
        }
        set.remove(CommandHandler.class);
        System.out.println(set);
    }
    
    protected void configureDispatch() {
        
    }
    
    protected void scan(Package pkg) {
        assert pkg != null;
        packages.add(pkg);
    }

}
