package us.gibb.dev.gwt.demo.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.demo.client.HelloCommand;
import us.gibb.dev.gwt.demo.client.HelloResult;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.CommandHandler;

import com.google.inject.Inject;

public class HelloCommandHandler extends CommandHandler<HelloCommand, HelloResult> {
    
    private EntityManagerFactory emf;

    @Inject
    public HelloCommandHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public HelloResult execute(HelloCommand command) throws CommandException {
        EntityManager em = emf.createEntityManager();
        try {
            Hello hello = new Hello();
            hello.setName(command.getName());
            em.persist(hello);
            return new HelloResult("Hello "+command.getName());
        } finally {
            em.close();
        }
    }

}
