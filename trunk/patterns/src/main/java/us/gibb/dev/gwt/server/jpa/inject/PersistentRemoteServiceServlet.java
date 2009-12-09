package us.gibb.dev.gwt.server.jpa.inject;

import javax.servlet.http.HttpServletRequest;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.gwt.PersistentRemoteService;
import us.gibb.dev.gwt.server.jpa.remote.JPAProcessCallHandler;
import us.gibb.dev.gwt.server.jpa.remote.JPARemoteServiceAdapter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class PersistentRemoteServiceServlet extends PersistentRemoteService implements JPARemoteServiceAdapter {
    private static final long serialVersionUID = 68052617558196206L;

    @Inject
    private Injector injector;

    @Inject
    private JPAProcessCallHandler processCallHandler;

    @Inject
    public PersistentRemoteServiceServlet(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    /**
     * Process call override. Use RPC_Copy instead. Copied from EngineRemoteService to change target object
     * from this to service
     */
    @Override
    public String processCall(String payload) throws SerializationException {
        return processCallHandler.processCall(payload, this);
    }

    @SuppressWarnings("unchecked")
    public RemoteService getRemoteService(Class serviceClass) {
        return (RemoteService) injector.getInstance(serviceClass);
    }
    
    @Override
    public HttpServletRequest getRequest() {
        return super.getThreadLocalRequest();
    }

    @Override
    public void onAfterRequestDeserialized(RPCRequest rpcRequest) {
        super.onAfterRequestDeserialized(rpcRequest);
    }
}