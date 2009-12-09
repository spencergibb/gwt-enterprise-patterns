package us.gibb.dev.gwt.server.jpa.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.gilead.gwt.PersistentRemoteService;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import us.gibb.dev.gwt.server.jpa.remote.JPAProcessCallHandler;
import us.gibb.dev.gwt.server.jpa.remote.JPARemoteServiceAdapter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;

/**
 * http://blog.digitalascent.com/2007/11/gwt-rpc-with-spring-2x_12.html
 * Spring HandlerAdapter to dispatch GWT-RPC requests.
 * 
 */
public class PersistentRcpHandlerAdapter extends PersistentRemoteService implements HandlerAdapter, ServletContextAware, JPARemoteServiceAdapter {

    private static ThreadLocal<Object> handlerHolder = new ThreadLocal<Object>();

    private ServletContext servletContext;
    
    private JPAProcessCallHandler processCallHandler;

    /**
     * Gets <code>ServletContext</code>.
     */
    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * Implementation of <code>ServletContextAware</code>.
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

	private static final long serialVersionUID = -7421136737990135393L;

	public long getLastModified(HttpServletRequest request, Object handler) {
		return -1;
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			// store the handler for retrieval in processCall()
            handlerHolder.set(handler);
			doPost(request, response);
		}
		finally {
			// clear out thread local to avoid resource leak
			handlerHolder.set(null);
		}

		return null;
	}

	protected Object getCurrentHandler() {
		return handlerHolder.get();
	}
	
	public boolean supports(Object handler) {
		return handler instanceof RemoteService;
	}

	@Override
	public String processCall(String payload) throws SerializationException {
	    return processCallHandler.processCall(payload, this);
	}

    @Override
    public RemoteService getRemoteService(Class<?> serviceClass) {
        return (RemoteService) getCurrentHandler();
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