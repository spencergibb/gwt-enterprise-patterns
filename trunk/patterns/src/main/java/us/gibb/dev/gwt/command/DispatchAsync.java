package us.gibb.dev.gwt.command;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface DispatchAsync {
    public <C extends Command<R>, R extends Result> void execute(C command, AsyncCallback<R> callback);
}
