package us.gibb.dev.gwt.server.remote;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;

public interface RemoteServiceAdapter extends SerializationPolicyProvider {
    RemoteService getRemoteService(Class<?> serviceClass);
    void onAfterRequestDeserialized(RPCRequest req);
    HttpServletRequest getRequest();
}
