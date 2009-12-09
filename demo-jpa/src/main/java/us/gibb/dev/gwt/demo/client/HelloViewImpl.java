package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.location.Location;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;

public class HelloViewImpl extends AbstractWidgetView<CommandEventBus> implements HelloPresenter.View {

    private Button button;
    private TextBox name;
    private TabLayoutPanel tabPanel;
    private int selectedTab;
    private Label response;
    
    @Inject
    public HelloViewImpl(final CommandEventBus eventBus, final GoodbyePresenter goodbye) {
        super(eventBus);
        
        response = new Label("N/A");
        name = new TextBox();
        button = new Button("Say Hello");
        HTML html = new HTML();
        Hyperlink sayGoodbye = new Hyperlink("Say goodbye", goodbye.getView().getLocation());

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(2);
        verticalPanel.add(name);
        verticalPanel.add(button);
        verticalPanel.add(response);
        verticalPanel.add(html);
        verticalPanel.add(sayGoodbye);
        
        tabPanel = new TabLayoutPanel(28.0, Unit.PX);
        tabPanel.add(verticalPanel, "Hello");
        tabPanel.add(goodbye.getView().getImpl(), "Goodbye");

        tabPanel.selectTab(0);
        
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            public void onSelection(SelectionEvent<Integer> event) {
                if (event.getSelectedItem() != selectedTab) {
                    selectedTab = event.getSelectedItem();
                    switch (event.getSelectedItem()) {
                    case 1:
                        eventBus.changeLocation(goodbye.getView().getLocation());
                        break;
                    default:
                        eventBus.changeLocation(getLocation());
                        break;
                    }
                }
            }});
        
        eventBus.add(new Location.Handler(getLocation()) {
            public void handle(Location location) {
                if (selectedTab != 0) {
                    tabPanel.selectTab(0);
                }
                if (location.getParam(0) != null) {
                    name.setValue(location.getParam(0));
                }
            }});
        
        eventBus.add(new Location.Handler(goodbye.getView().getLocation()) {
            public void handle(Location location) {
                if (selectedTab != 1) {
                    tabPanel.selectTab(1);
                }
            }});
        
        DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.PCT);
        dockPanel.addWest(new HTML(), 20.0);
        dockPanel.addEast(new HTML(), 20.0);
        dockPanel.add(tabPanel);

        initWidget(dockPanel);
    }
    
    public HasClickHandlers getButton() {
        return button;
    }
    
    @Override
    public HasText getName() {
        return name;
    }
    
    @Override
    public HasText getResult() {
        return response;
    }

    @Override
    public String getLocation() {
        return "hello";
    }

}
