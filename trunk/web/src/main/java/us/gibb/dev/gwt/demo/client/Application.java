package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.event.DefaultEventBus;
import us.gibb.dev.gwt.event.EventBus;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
	private static final String APP_ID = "application";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
    	Element loading = getLoading();
	    try {
	    	// http://turbomanage.wordpress.com/2009/10/13/how-to-create-a-splash-screen-while-gwt-loads/
	    	// Set GWT container invisible
	    	if (loading != null)
	    		DOM.setStyleAttribute(RootPanel.get(APP_ID).getElement(), "display", "none");

	    	// Create a Ginjector
			//Injector injector = GWT.create(Injector.class);
			// Add the application display to the RootPanel
			//RootPanel.get(APP_ID).add(injector.getHelloPresenter().getView().widget());
	    	RootPanel.get(APP_ID).add(getWidget());

			displayApp(loading);
		}
		catch (Exception e) {
			e.printStackTrace();
			displayApp(loading);
			RootPanel.get(APP_ID).add(new HTML("<pre>ERROR\n"+e.getMessage()+"</pre>"));
		}
	}

    private Widget getWidget() {
        final Label labelA = new Label("Label A");
        final Label labelB = new Label("Label B");
        Button buttonA = new Button("Click me");
        
        final EventBus bus = new DefaultEventBus();
        
        bus.addHandler(CommandA.TYPE, new CommandA.Handler() {
            public void handle(CommandA event) {
                labelA.setText(labelA.getText()+ " "+event.getData());
            }});
        
        buttonA.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                bus.fire(new CommandA());
            }});
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(labelA);
        verticalPanel.add(labelB);
        verticalPanel.add(buttonA);
        return verticalPanel;
    }

	private void displayApp(Element loading) {
    	if (loading != null) {
			//remove loading image
			DOM.removeChild(DOM.getParent(loading), loading);
	
			// Set GWT container visible
			DOM.setStyleAttribute(RootPanel.get(APP_ID).getElement(), "display", "block");
    	}
	}

	private Element getLoading() {
		try {
			return DOM.getElementById("loading");
		}
		catch (Exception e) {
		}
		return null;
	}
}
