package us.gibb.dev.gwt.server.inject;

import java.lang.reflect.InvocationTargetException;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.gwt.GileadRPCHelper;
import net.sf.gilead.gwt.PersistentRemoteService;
import us.gibb.dev.gwt.server.gwtrpc2_0.RPCGilead;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class PersistentRemoteServiceServlet extends PersistentRemoteService {
    private static final long serialVersionUID = 68052617558196206L;

    @Inject
    private Injector injector;
    
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
        // Normal processing
        RPCRequest req = null;
        try {
            // Decode request
            req = RPCGilead.decodeRequest(payload, null, this);
            onAfterRequestDeserialized(req);
            RemoteService service = getServiceInstance(req.getMethod().getDeclaringClass());

            // Invoke method
            GileadRPCHelper.parseInputParameters(req, _beanManager, getThreadLocalRequest().getSession());
            Object returnValue = RPCGilead.invoke(service, req.getMethod(), req.getParameters(),
                    req.getSerializationPolicy());

            returnValue = GileadRPCHelper.parseReturnValue(returnValue, _beanManager);

            // Encode response
            return RPCGilead.encodeResponseForSuccess(req.getMethod(), returnValue,
                    req.getSerializationPolicy());

        } catch (InvocationTargetException e) {
            return RPCGilead.encodeResponseForFailure(req.getMethod(), e.getCause(),
                    req.getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            if (req != null) {
                return RPCGilead.encodeResponseForFailure(null, ex, req.getSerializationPolicy());
            } else {
                return RPCGilead.encodeResponseForFailure(null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private RemoteService getServiceInstance(Class serviceClass) {
        return (RemoteService) injector.getInstance(serviceClass);
    }
}