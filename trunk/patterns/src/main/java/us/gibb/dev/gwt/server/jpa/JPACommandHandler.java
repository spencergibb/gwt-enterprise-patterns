package us.gibb.dev.gwt.server.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.server.command.handler.AbstractCommandHandler;
import us.gibb.dev.gwt.server.command.handler.Context;

public abstract class JPACommandHandler<C extends Command<R>, R extends Result> extends AbstractCommandHandler<C, R> {
    
    private EntityManagerFactory emf;
    private boolean transactional;

    public JPACommandHandler(EntityManagerFactory emf) {
        this(emf, true);
    }

    public JPACommandHandler(EntityManagerFactory emf, boolean transactional) {
        this.emf = emf;
        this.transactional = transactional;
    }

    @Override
    public R execute(C command, Context context) throws CommandException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        if (transactional) { 
            tx = em.getTransaction();
            tx.begin();
        }
        try {
            return execute(em, command, context);
        } catch (Exception e) {
            e.printStackTrace(); //TODO log error
            if (transactional) tx.rollback();
        } finally {
            if (transactional) tx.commit();
            em.close();
        }
        return null;
    }

    protected abstract R execute(EntityManager em, C command, Context context) throws CommandException;

}
