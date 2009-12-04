package us.gibb.dev.gwt.demo.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.Context;
import us.gibb.dev.gwt.server.jpa.JPACommandHandler;

import com.google.inject.Inject;

public class GetHelloCommandHandler extends JPACommandHandler<GetHelloCommand, HelloResult> {

    @Inject
    public GetHelloCommandHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected HelloResult execute(EntityManager em, GetHelloCommand command, Context context) {
        Query query = em.createQuery("select q from Hello q where q.name = :name order by q.createdDate desc");
        query.setMaxResults(1); //only get the latest
        query.setParameter("name", command.getName());
        List<Hello> results = query.getResultList();
        if (results == null || results.isEmpty()) {
            return new HelloResult(null);
        }
        return new HelloResult(results.get(0));
    }

}
