package us.gibb.dev.gwt.server.jpa.remote;

import net.sf.gilead.core.PersistentBeanManager;
import us.gibb.dev.gwt.server.remote.RemoteServiceAdapter;

public interface JPARemoteServiceAdapter extends RemoteServiceAdapter {
    PersistentBeanManager getBeanManager();
}
