package us.gibb.dev.gwt.server.inject;

import net.sf.gilead.adapter4appengine.EngineRemoteService;
import net.sf.gilead.adapter4appengine.datanucleus.JdoEntityStore;
import us.gibb.dev.gwt.server.gwtrpc2_0.RPCAppengine;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class AppengineRemoteServiceServlet extends EngineRemoteService {
    private static final long serialVersionUID = 68052617558196206L;

    @Inject
    private Injector injector;
    
    /**
     * Process call override. Use RPC_Copy instead.  Copied from EngineRemoteService to change
     * target object from this to service
     */
    @Override
    public String processCall(String payload) throws SerializationException {
        
        //Set HTTP session
        JdoEntityStore.getInstance().setHttpSession(getThreadLocalRequest().getSession());
        
        try {
            RPCRequest req = RPCAppengine.decodeRequest(payload, null, this);
            onAfterRequestDeserialized(req);
            RemoteService service = getServiceInstance(req.getMethod().getDeclaringClass());
            return RPCAppengine.invokeAndEncodeResponse(service, req.getMethod(), 
                    req.getParameters(), req.getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            log("IncompatibleRemoteServiceException in the processCall(String) method.", ex);
            return RPC.encodeResponseForFailure(null, ex);
        }
        finally
        {
            //Set Http session to null
            JdoEntityStore.getInstance().setHttpSession(null);
        }
    }


    @SuppressWarnings("unchecked")
    private RemoteService getServiceInstance(Class serviceClass) {
        return (RemoteService) injector.getInstance(serviceClass);
    }
}