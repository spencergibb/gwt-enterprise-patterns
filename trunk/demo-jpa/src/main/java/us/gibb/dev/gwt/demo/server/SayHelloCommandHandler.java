package us.gibb.dev.gwt.demo.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import us.gibb.dev.gwt.command.results.StringResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.jpa.JPACommandHandler;

import com.google.inject.Inject;

public class SayHelloCommandHandler extends JPACommandHandler<SayHelloCommand, StringResult> {

    @Inject
    public SayHelloCommandHandler(EntityManagerFactory emf) {
        super(emf);
    }
    
    @Override
    protected StringResult execute(EntityManager em, SayHelloCommand command, Context context) {
        Hello hello = new Hello();
        hello.setName(command.getName());
        em.persist(hello);
        return new StringResult(hello.toString());
    }

}
