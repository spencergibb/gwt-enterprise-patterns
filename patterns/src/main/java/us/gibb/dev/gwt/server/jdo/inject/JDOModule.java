package us.gibb.dev.gwt.server.jdo.inject;

import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.server.appengine.remote.AppengineProcessCallHandler;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class JDOModule extends AbstractModule {

    private String persistenceUnitName;

    public JDOModule(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("persistence.unit.name")).to(persistenceUnitName);
        bind(AppengineProcessCallHandler.class).asEagerSingleton(); //TODO uncouple from JDO
        bind(PersistenceManagerFactory.class).toProvider(PersistenceManagerFactoryProvider.class).in(Singleton.class);

        configureJDO();
    }

    protected void configureJDO() {
    }

}
