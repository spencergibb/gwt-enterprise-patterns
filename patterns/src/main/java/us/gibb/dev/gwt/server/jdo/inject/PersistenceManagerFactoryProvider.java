package us.gibb.dev.gwt.server.jdo.inject;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class PersistenceManagerFactoryProvider implements Provider<PersistenceManagerFactory> {

    private String persistenceUnitName;

    @Inject
    PersistenceManagerFactoryProvider(@Named("persistence.unit.name") String persistenceUnitName)  {
        this.persistenceUnitName = persistenceUnitName;
    }
    
    @Override
    public PersistenceManagerFactory get() {
        return JDOHelper.getPersistenceManagerFactory(persistenceUnitName);
    }

}
