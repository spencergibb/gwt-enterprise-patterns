package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RecipeViewImpl extends AbstractWidgetView<CommandEventBus> implements RecipePresenter.View {

    public interface RecipeViewUiBinder extends UiBinder<Widget, RecipeViewImpl> { }

    @UiField HasClickHandlers save;
    @UiField TextBox name;
    @UiField TextBox duration;
    @UiField TextArea text;
    @UiField HasText id;
    
    @Inject
    public RecipeViewImpl(final CommandEventBus eventBus,
            RecipeViewUiBinder uiBinder) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasText getName() {
        return name;
    }

    @Override
    public HasText getId() {
        return id;
    }

    @Override
    public HasClickHandlers getSave() {
        return save;
    }

    @Override
    public HasText getText() {
        return text;
    }
    
    @Override
    public HasText getDuration() {
        return duration;
    }
    
    @Override
    public String getLocation() {
        return "edit";
    }

}
