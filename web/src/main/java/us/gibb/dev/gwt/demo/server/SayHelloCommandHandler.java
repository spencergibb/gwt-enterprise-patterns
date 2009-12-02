package us.gibb.dev.gwt.demo.server;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.inject.DispatchIgnore;
import us.gibb.dev.gwt.server.jdo.JDOCommandHandler;

import com.google.inject.Inject;

@DispatchIgnore
public class SayHelloCommandHandler extends JDOCommandHandler<SayHelloCommand, HelloResult> {
    
    @Inject
    public SayHelloCommandHandler(PersistenceManagerFactory pmf) {
        super(pmf);
    }
    
    @Override
    protected HelloResult execute(PersistenceManager pm, SayHelloCommand command) {
        Hello hello = new Hello();
        hello.setName(command.getName());
        hello.setCreatedDate(new Date());
        pm.makePersistent(hello);
        return new HelloResult(hello);
    }

    public static void main(String[] args) {
        boolean assignableFrom = SayHelloCommand.class.isAssignableFrom(Command.class);
        System.out.println(assignableFrom);
        assignableFrom = Command.class.isAssignableFrom(SayHelloCommand.class);
        System.out.println(assignableFrom);
        
        assignableFrom = Result.class.isAssignableFrom(HelloResult.class);
        System.out.println(assignableFrom);
    }
}
