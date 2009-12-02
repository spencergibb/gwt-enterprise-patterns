package us.gibb.dev.gwt.demo.server;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.Context;
import us.gibb.dev.gwt.server.MultiCommandHandler;

import com.google.inject.Inject;

public class HelloCommandHandler extends MultiCommandHandler {
    
    private PersistenceManagerFactory pmf;
    
    @Inject
    public HelloCommandHandler(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }
    
    public HelloResult sayHello(SayHelloCommand command, Context context) {
        PersistenceManager pm = getPM();
        Hello hello = new Hello();
        hello.setName(command.getName());
        hello.setCreatedDate(new Date());
        pm.makePersistent(hello);
        return new HelloResult(hello);
    }

    @SuppressWarnings("unchecked")
    public HelloResult getHello(GetHelloCommand command, Context context) {
        PersistenceManager pm = getPM();
        Query query = pm.newQuery(Hello.class, "name == nameParam");
        query.setOrdering("createdDate desc");
        query.declareParameters("String nameParam");
        query.setRange(0, 1); // only get the latest
        List<Hello> results = (List<Hello>) query.execute(command.getName());
        if (results == null || results.isEmpty()) {
            return new HelloResult(null);
        }
        return new HelloResult(results.get(0));
    }

    private PersistenceManager getPM() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.setDetachAllOnCommit(true);
        return pm;
    }
    
}
