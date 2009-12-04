package us.gibb.dev.gwt.server.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.wideplay.warp.persist.Transactional;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.server.AbstractCommandHandler;
import us.gibb.dev.gwt.server.Context;

public abstract class JPACommandHandler<C extends Command<R>, R extends Result> extends AbstractCommandHandler<C, R> {
    
    private EntityManagerFactory emf;

    public JPACommandHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    @Transactional
    public R execute(C command, Context context) throws CommandException {
        EntityManager em = emf.createEntityManager();
        try {
            return execute(em, command, context);
        } finally {
            em.close();
        }
    }

    protected abstract R execute(EntityManager em, C command, Context context);

}
