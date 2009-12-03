package us.gibb.dev.gwt.demo.server;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.command.results.StringResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.Context;
import us.gibb.dev.gwt.server.inject.DispatchIgnore;
import us.gibb.dev.gwt.server.jdo.JDOCommandHandler;

import com.google.inject.Inject;

@DispatchIgnore
public class SayHelloCommandHandler extends JDOCommandHandler<SayHelloCommand, StringResult> {
    
    @Inject
    public SayHelloCommandHandler(PersistenceManagerFactory pmf) {
        super(pmf);
    }
    
    @Override
    protected StringResult execute(PersistenceManager pm, SayHelloCommand command, Context context) {
        Hello hello = new Hello();
        hello.setName(command.getName());
        hello.setCreatedDate(new Date());
        pm.makePersistent(hello);
        return new StringResult("Hello "+hello.getName());
    }
}
