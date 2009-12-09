package us.gibb.dev.gwt.server.jpa.remote;

import java.lang.reflect.InvocationTargetException;

import net.sf.gilead.gwt.GileadRPCHelper;
import us.gibb.dev.gwt.server.remote.ProcessCallHandler;
import us.gibb.dev.gwt.server.remote.RPCGilead;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;

public class JPAProcessCallHandler implements ProcessCallHandler<JPARemoteServiceAdapter> {

    /**
     * Process call override. Use RPC_Copy instead. Copied from EngineRemoteService to change target object
     * from this to service
     */
    public String processCall(String payload, JPARemoteServiceAdapter adapter) throws SerializationException {
        // Normal processing
        RPCRequest req = null;
        try {
            // Decode request
            req = RPCGilead.decodeRequest(payload, null, adapter);
            adapter.onAfterRequestDeserialized(req);
            RemoteService service = adapter.getRemoteService(req.getMethod().getDeclaringClass());

            // Invoke method
            GileadRPCHelper.parseInputParameters(req, adapter.getBeanManager(), adapter.getRequest().getSession());
            Object returnValue = RPCGilead.invoke(service, req.getMethod(), req.getParameters(), req
                    .getSerializationPolicy());

            returnValue = GileadRPCHelper.parseReturnValue(returnValue, adapter.getBeanManager());

            // Encode response
            return RPCGilead.encodeResponseForSuccess(req.getMethod(), returnValue, req
                    .getSerializationPolicy());

        } catch (InvocationTargetException e) {
            return RPCGilead.encodeResponseForFailure(req.getMethod(), e.getCause(), req
                    .getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            if (req != null) {
                return RPCGilead.encodeResponseForFailure(null, ex, req.getSerializationPolicy());
            } else {
                return RPCGilead.encodeResponseForFailure(null, ex);
            }
        }
    }

}
