package us.gibb.dev.gwt.server.appengine.remote;

import net.sf.gilead.adapter4appengine.datanucleus.JdoEntityStore;
import us.gibb.dev.gwt.server.remote.ProcessCallHandler;
import us.gibb.dev.gwt.server.remote.RPCAppengine;
import us.gibb.dev.gwt.server.remote.RemoteServiceAdapter;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class AppengineProcessCallHandler implements ProcessCallHandler<RemoteServiceAdapter> {

    /**
     * Process call override. Use RPC_Copy instead.  Copied from EngineRemoteService to change
     * target object from this to service
     */
    @Override
    public String processCall(String payload, RemoteServiceAdapter adapter) throws SerializationException {
        //Set HTTP session
        JdoEntityStore.getInstance().setHttpSession(adapter.getRequest().getSession());
        
        try {
            RPCRequest req = RPCAppengine.decodeRequest(payload, null, adapter);
            adapter.onAfterRequestDeserialized(req);
            RemoteService service = adapter.getRemoteService(req.getMethod().getDeclaringClass());
            return RPCAppengine.invokeAndEncodeResponse(service, req.getMethod(), 
                    req.getParameters(), req.getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            //log("IncompatibleRemoteServiceException in the processCall(String) method.", ex);
            return RPC.encodeResponseForFailure(null, ex);
        }
        finally
        {
            //Set Http session to null
            JdoEntityStore.getInstance().setHttpSession(null);
        }
    }

}
