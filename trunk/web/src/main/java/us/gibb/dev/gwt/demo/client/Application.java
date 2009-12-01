package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.demo.client.HelloPresenter.View;
import us.gibb.dev.gwt.demo.client.inject.Injector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
	private static final String LOADING_ID = "loading";
    private static final String APP_ID = "application";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
    	Element loading = getLoadingElement(LOADING_ID);
	    try {
	    	// http://turbomanage.wordpress.com/2009/10/13/how-to-create-a-splash-screen-while-gwt-loads/
	    	// Set GWT container invisible only if there is a loading element
	    	if (loading != null) {
	    		DOM.setStyleAttribute(RootPanel.get(APP_ID).getElement(), "display", "none");
	    	}

	    	// Create a Ginjector
			Injector injector = GWT.create(Injector.class);
			
			// Add the application display to the RootPanel. must call get*Presenter first,
			// otherwise injection is screwed up
			View view = injector.getHelloPresenter().getView();
			
			// bootstrap the currentLocation
			injector.getEventBus().fireCurrentLocation();
			
            RootPanel.get(APP_ID).add(view.getImpl());

			displayApp(loading, APP_ID);
		}
		catch (Exception e) {
			handleException(e, loading, APP_ID);
		}
	}

	protected Element getLoadingElement(String id) {
		try {
			return DOM.getElementById(id);
		}
		catch (Exception e) {
		}
		return null;
	}

	protected void displayApp(Element loadingElement, String appId) {
        if (loadingElement != null) {
            //remove loading image
            DOM.removeChild(DOM.getParent(loadingElement), loadingElement);
    
            // Set GWT container visible
            DOM.setStyleAttribute(RootPanel.get(appId).getElement(), "display", "block");
        }
    }

	protected void handleException(Exception e, Element loading, String appId) {
        e.printStackTrace();
        displayApp(loading, appId);
        RootPanel.get(appId).add(new HTML("<pre>ERROR\n"+e.getMessage()+"</pre>"));
    }
}
