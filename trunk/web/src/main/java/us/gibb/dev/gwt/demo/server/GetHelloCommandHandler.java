package us.gibb.dev.gwt.demo.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.GetHelloResult;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.jdo.JDOCommandHandler;

import com.google.inject.Inject;

public class GetHelloCommandHandler extends JDOCommandHandler<GetHelloCommand, GetHelloResult> {

    @Inject
    public GetHelloCommandHandler(PersistenceManagerFactory pmf) {
        super(pmf);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected GetHelloResult execute(PersistenceManager pm, GetHelloCommand command) {
        Query query = pm.newQuery(Hello.class, "name == nameParam");
        query.setOrdering("createdDate desc");
        query.declareParameters("String nameParam");
        query.setRange(0, 1); // only get the latest
        List<Hello> results = (List<Hello>) query.execute(command.getName());
        if (results == null || results.isEmpty()) {
            return new GetHelloResult(null);
        }
        return new GetHelloResult(results.get(0));
    }

}
