package us.gibb.dev.gwt.server.jpa.inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import net.sf.gilead.core.PersistentBeanManager;
import us.gibb.dev.gwt.server.jpa.remote.JPAProcessCallHandler;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class JPAModule extends AbstractModule {

    private String persistenceUnitName;

    public JPAModule(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("persistence.unit.name")).to(persistenceUnitName);
        bind(JPAProcessCallHandler.class).asEagerSingleton();
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class).in(Singleton.class);
        bind(EntityManager.class).toProvider(EntityManagerProvider.class).in(Singleton.class);
        bind(PersistentBeanManager.class).toProvider(PersistentBeanManagerProvider.class).in(Singleton.class);

        configureJPA();
    }

    protected void configureJPA() {
    }

}
