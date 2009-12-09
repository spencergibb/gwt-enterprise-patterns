package us.gibb.dev.gwt.server.jpa.inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class EntityManagerProvider implements Provider<EntityManager> {


    private EntityManagerFactory emf;

    @Inject
    EntityManagerProvider(EntityManagerFactory emf)  {
        this.emf = emf;
    }
    
    @Override
    public EntityManager get() {
        return emf.createEntityManager();
    }

}
