package us.gibb.dev.gwt.server.jdo;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.server.command.handler.AbstractCommandHandler;
import us.gibb.dev.gwt.server.command.handler.Context;

public abstract class JDOCommandHandler<C extends Command<R>, R extends Result> extends AbstractCommandHandler<C, R> {
    
    private PersistenceManagerFactory pmf;

    public JDOCommandHandler(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    @Override
    public R execute(C command, Context context) throws CommandException {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.setDetachAllOnCommit(true);
        try {
            return execute(pm, command, context);
        } finally {
            pm.close();
        }
    }

    protected abstract R execute(PersistenceManager pm, C command, Context context);

}
