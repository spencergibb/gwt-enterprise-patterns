package us.gibb.dev.gwt.demo.server;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.demo.client.command.SayHelloResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.jdo.JDOCommandHandler;

import com.google.inject.Inject;

public class SayHelloCommandHandler extends JDOCommandHandler<SayHelloCommand, SayHelloResult> {
    
    @Inject
    public SayHelloCommandHandler(PersistenceManagerFactory pmf) {
        super(pmf);
    }
    
    @Override
    protected SayHelloResult execute(PersistenceManager pm, SayHelloCommand command) {
        Hello hello = new Hello();
        hello.setName(command.getName());
        hello.setCreatedDate(new Date());
        pm.makePersistent(hello);
        return new SayHelloResult(hello);
    }
    
}
