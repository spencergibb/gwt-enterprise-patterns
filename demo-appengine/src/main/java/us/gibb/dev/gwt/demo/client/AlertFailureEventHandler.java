/**
 * 
 */
package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.event.FailureEvent;

import com.google.gwt.user.client.Window;

final class AlertFailureEventHandler extends FailureEvent.Handler {
    public void handle(FailureEvent event) {
        StringBuilder out = new StringBuilder();
        if (event.getMessage() != null) {
            out.append(event.getMessage());
        }
        if (event.getMessage() != null && event.getThrowable() != null) {
            out.append(" ");
        }
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            out.append("Caused by: ");
            out.append(event.getThrowable());
        }
        Window.alert(out.toString());
    }
}