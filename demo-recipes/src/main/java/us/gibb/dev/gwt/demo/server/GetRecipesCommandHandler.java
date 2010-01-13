package us.gibb.dev.gwt.demo.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import us.gibb.dev.gwt.demo.client.command.GetRecipesCommand;
import us.gibb.dev.gwt.demo.client.command.RecipesResult;
import us.gibb.dev.gwt.demo.client.command.RecipesResult.Row;
import us.gibb.dev.gwt.demo.model.Recipe;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.jpa.JPACommandHandler;

import com.google.inject.Inject;

public class GetRecipesCommandHandler extends JPACommandHandler<GetRecipesCommand, RecipesResult> {

    @Inject
    public GetRecipesCommandHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected RecipesResult execute(EntityManager em, GetRecipesCommand command, Context context) {
        Query query = em.createQuery("select q from Recipe q order by q.createdDate desc");
        query.setMaxResults(10); //only get the latest
        //query.setParameter("name", command.getName());
        List<Recipe> results = query.getResultList();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>(results);
        if (recipes.isEmpty()) {
            Recipe r = new Recipe();
            r.setId(3001L);
            r.setName("My recipe");
            r.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam varius malesuada justo quis euismod. Nunc mattis iaculis sem, et dignissim dolor placerat vel. Nam fermentum malesuada diam, non scelerisque eros feugiat ac. Aenean auctor ligula a eros dictum vulputate et vitae sapien. Fusce pulvinar porttitor neque sit amet tristique. Etiam.");
            r.setCreatedDate(new Date());
            recipes.add(r);
            r = new Recipe();
            r.setId(3002L);
            r.setName("Your recipe");
            r.setCreatedDate(new Date());
            r.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam varius malesuada justo quis euismod. Nunc mattis iaculis sem, et dignissim dolor placerat vel. Nam fermentum malesuada diam, non scelerisque eros feugiat ac. Aenean auctor ligula a eros dictum vulputate et vitae sapien. Fusce pulvinar porttitor neque sit amet tristique. Etiam.");
            recipes.add(r);
            r = new Recipe();
            r.setId(3003L);
            r.setName("Our recipe");
            r.setCreatedDate(new Date());
            r.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam varius malesuada justo quis euismod. Nunc mattis iaculis sem, et dignissim dolor placerat vel. Nam fermentum malesuada diam, non scelerisque eros feugiat ac. Aenean auctor ligula a eros dictum vulputate et vitae sapien. Fusce pulvinar porttitor neque sit amet tristique. Etiam.");
            recipes.add(r);
        }
        ArrayList<Row> rows = new ArrayList<Row>();
        for (Recipe recipe : recipes) {
            Row row = new Row();
            row.id = recipe.getId();
            row.name = recipe.getName();
            row.createdDate = recipe.getCreatedDate();
            row.duration = recipe.getDuration();
            rows.add(row);
        }
        return new RecipesResult(rows);
    }

}
