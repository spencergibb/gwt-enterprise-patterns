package us.gibb.dev.gwt.demo.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.demo.client.HelloCommand;
import us.gibb.dev.gwt.demo.client.HelloResult;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.CommandHandler;

import com.google.inject.Inject;

public class HelloCommandHandler extends CommandHandler<HelloCommand, HelloResult> {
    
    private PersistenceManagerFactory pmf;

    @Inject
    public HelloCommandHandler(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    @Override
    public HelloResult execute(HelloCommand command) throws CommandException {
        PersistenceManager pm = pmf.getPersistenceManager();
        try {
            Hello hello = new Hello();
            hello.setName(command.getName());
            pm.makePersistent(hello);
            return new HelloResult("Hello "+command.getName());
        } finally {
            pm.close();
        }
    }
    
}
