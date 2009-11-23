package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.command.ResultEvent;
import us.gibb.dev.gwt.event.FailureEvent;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

public class HelloViewImpl extends AbstractWidgetView<CommandEventBus> implements HelloPresenter.View {

    private Button button;
    private TextBox name;

    @Inject
    public HelloViewImpl(CommandEventBus eventBus) {
        super(eventBus);
        
        final Label label = new Label("N/A");
        name = new TextBox();
        button = new Button("Say Hello");
        HTML html = new HTML();
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(name);
        verticalPanel.add(button);
        verticalPanel.add(label);
        verticalPanel.add(html);
        
        eventBus.add(new ResultEvent.Handler<HelloResult>(HelloResult.class){
            public void handle(ResultEvent<HelloResult> event) {
                label.setText(event.getResult().getHello());
            }});
        
        eventBus.add(new FailureEvent.Handler() {
            public void handle(FailureEvent event) {
                StringBuilder out = new StringBuilder();
                if (event.getMessage() != null) {
                    out.append(event.getMessage());
                }
                if (event.getMessage() != null && event.getThrowable() != null) {
                    out.append(" ");
                }
                if (event.getThrowable() != null) {
                    out.append("Caused by: ");
                    out.append(event.getThrowable());
                }
                Window.alert(out.toString());
            }});

        initWidget(verticalPanel);
    }
    
    public HasClickHandlers getButton() {
        return button;
    }
    
    @Override
    public HasValue<String> getName() {
        return name;
    }

}
