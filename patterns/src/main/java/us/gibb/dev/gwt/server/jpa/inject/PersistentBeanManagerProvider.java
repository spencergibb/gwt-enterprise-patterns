package us.gibb.dev.gwt.server.jpa.inject;

import javax.persistence.EntityManagerFactory;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateJpaUtil;
import net.sf.gilead.core.store.stateless.StatelessProxyStore;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PersistentBeanManagerProvider implements Provider<PersistentBeanManager> {

    private EntityManagerFactory emf;
    
    @Inject
    public PersistentBeanManagerProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public PersistentBeanManager get() {
        HibernateJpaUtil persistenceUtil = new HibernateJpaUtil();

        persistenceUtil.setEntityManagerFactory(emf);

        PersistentBeanManager beanManager = new PersistentBeanManager();

        beanManager.setPersistenceUtil(persistenceUtil);

        beanManager.setProxyStore(new StatelessProxyStore());
        return beanManager;
    }

}
