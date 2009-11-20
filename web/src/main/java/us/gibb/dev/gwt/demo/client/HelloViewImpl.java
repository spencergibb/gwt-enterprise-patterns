package us.gibb.dev.gwt.demo.client;

import java.util.HashMap;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

public class HelloViewImpl extends AbstractWidgetView<CommandEventBus> implements HelloPresenter.View {

    private Button buttonA;

    @Inject
    public HelloViewImpl(CommandEventBus eventBus) {
        super(eventBus);
        
        final Label labelA = new Label("Label A");
        final Label labelB = new Label("Label B");
        buttonA = new Button("Click me");
        HTML html = new HTML();
        
        eventBus.add(new ResultEvent.Handler<ResultA>(ResultA.class){
            public void handle(ResultEvent<ResultA> event) {
                labelA.setText(labelA.getText()+ " "+event.getResult().getA());
            }});
        
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put(CommandA.class, new CommandA());
        map.put(CommandB.class, new CommandB());
        
        StringBuilder out = new StringBuilder("<pre>");
        out.append("CommandA isHandled: "+eventBus.isHandled(CommandA.class)+"\n");
        for (Object key : map.keySet()) {
            Object val = map.get(key);
            out.append(key+" "+val.getClass().getName()+"\n");
        }
        out.append("</pre>");
        html.setHTML(out.toString());

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(labelA);
        verticalPanel.add(labelB);
        verticalPanel.add(buttonA);
        verticalPanel.add(html);

        initWidget(verticalPanel);
    }
    
    public HasClickHandlers getButton() {
        return buttonA;
    }

}
