package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

public class GoodbyeViewImpl extends AbstractWidgetView<CommandEventBus> implements GoodbyePresenter.View {
    private TextBox name;
    private Button button;
    private Label result;

    @Inject
    public GoodbyeViewImpl(CommandEventBus eventBus) {
        super(eventBus);
        
        //final Label label = new Label("N/A");
        name = new TextBox();
        button = new Button("Get Hello");
        result = new Label("N/A");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(name);
        verticalPanel.add(button);
        verticalPanel.add(result);
        
        initWidget(verticalPanel);
    }
    
    @Override
    public HasText getName() {
        return name;
    }
    
    @Override
    public HasText getResult() {
        return result;
    }

    @Override
    public String getLocation() {
        return "goodbye";
    }
    
    public HasClickHandlers getButton() {
        return button;
    }

}
