package us.gibb.dev.gwt.command;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface Command<R extends Result> extends Serializable, IsSerializable {

}
