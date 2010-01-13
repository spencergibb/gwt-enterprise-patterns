package us.gibb.dev.gwt.demo.server;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.gibb.dev.gwt.command.results.StringResult;
import us.gibb.dev.gwt.demo.client.command.GetHelloCommand;
import us.gibb.dev.gwt.demo.client.command.HelloResult;
import us.gibb.dev.gwt.demo.client.command.SayHelloCommand;
import us.gibb.dev.gwt.demo.model.Hello;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.command.handler.MultiCommandHandler;

@Service("helloCommandHandler")
//@Component("helloCommandHandler")
//@Scope("request")
public class HelloCommandHandlerImpl extends MultiCommandHandler implements HelloCommandHandler {
    
    @PersistenceContext
    private EntityManager em;
    
    public HelloCommandHandlerImpl() {
    }
    
    @Transactional
    public StringResult sayHello(SayHelloCommand command, Context context) {
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Hello hello = new Hello();
            hello.setName(command.getName());
            hello.setCreatedDate(new Date());
            em.persist(hello);
            tx.commit();
            em.close();
            return new StringResult(hello.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public HelloResult getHello(GetHelloCommand command, Context context) {
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
