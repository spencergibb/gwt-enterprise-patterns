package us.gibb.dev.gwt.server.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * http://blog.digitalascent.com/2007/11/gwt-rpc-with-spring-2x_12.html
 * Spring HandlerAdapter to dispatch GWT-RPC requests.
 * 
 */
public class GwtRcpHandlerAdapter extends RemoteServiceServlet implements HandlerAdapter, ServletContextAware {

	private static ThreadLocal<Object> handlerHolder = new ThreadLocal<Object>();

    private ServletContext servletContext;

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
		/*
		 * The code below is borrowed from RemoteServiceServlet.processCall, with
		 * the following changes:
		 * 
		 * 1) Changed object for decoding and invocation to be the handler
		 * (versus the original 'this')
		 */

		try {
			RPCRequest rpcRequest = RPC.decodeRequest(payload, getCurrentHandler().getClass(), this);
			onAfterRequestDeserialized(rpcRequest);
			String retVal = RPC.invokeAndEncodeResponse(getCurrentHandler(), rpcRequest.getMethod(), rpcRequest
					.getParameters(), rpcRequest.getSerializationPolicy());

			return retVal;

		}
		catch (IncompatibleRemoteServiceException e) {
			return RPC.encodeResponseForFailure(null, e);
		}
	}
}