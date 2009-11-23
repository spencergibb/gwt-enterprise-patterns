package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

public class GoodbyeViewImpl extends AbstractWidgetView<CommandEventBus> implements GoodbyePresenter.View {
    private TextBox name;

    @Inject
    public GoodbyeViewImpl(CommandEventBus eventBus) {
        super(eventBus);
        
        //final Label label = new Label("N/A");
        name = new TextBox();
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(name);
        
        /*eventBus.add(new ResultEvent.Handler<HelloResult>(HelloResult.class){
            public void handle(ResultEvent<HelloResult> event) {
                label.setText(event.getResult().getHello());
            }});*/
        
        initWidget(verticalPanel);
    }
    
    @Override
    public HasValue<String> getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return "goodbye";
    }

}
