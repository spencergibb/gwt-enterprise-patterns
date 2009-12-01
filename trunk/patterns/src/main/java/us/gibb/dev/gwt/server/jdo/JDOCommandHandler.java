package us.gibb.dev.gwt.server.jdo;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import us.gibb.dev.gwt.command.Command;
import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.server.CommandHandler;
import us.gibb.dev.gwt.server.inject.DispatchIgnore;

@DispatchIgnore
public abstract class JDOCommandHandler<C extends Command<R>, R extends Result> extends CommandHandler<C, R> {
    
    private PersistenceManagerFactory pmf;

    public JDOCommandHandler(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    @Override
    public R execute(C command) throws CommandException {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.setDetachAllOnCommit(true);
        try {
            return execute(pm, command);
        } finally {
            pm.close();
        }
    }

    protected abstract R execute(PersistenceManager pm, C command);

}
