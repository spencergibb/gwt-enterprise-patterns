package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.location.Location;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class HelloViewImpl extends AbstractWidgetView<CommandEventBus> implements HelloPresenter.View {

    private static HelloViewImplUiBinder uiBinder = GWT.create(HelloViewImplUiBinder.class);

    interface HelloViewImplUiBinder extends UiBinder<Widget, HelloViewImpl> { }

    @UiField Button button;
    @UiField TextBox name;
    @UiField TabLayoutPanel tabPanel;
    @UiField Label response;
    @UiField Hyperlink sayGoodbye;
    private int selectedTab;
    
    @Inject
    public HelloViewImpl(final CommandEventBus eventBus, final GoodbyePresenter goodbye) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));

        sayGoodbye.setTargetHistoryToken(goodbye.getView().getLocation());
        
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
