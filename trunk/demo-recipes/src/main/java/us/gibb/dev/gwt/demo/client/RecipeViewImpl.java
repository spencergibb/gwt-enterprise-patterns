package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.CommandEventBus;
import us.gibb.dev.gwt.demo.model.Recipe.Duration;
import us.gibb.dev.gwt.view.AbstractWidgetView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class RecipeViewImpl extends AbstractWidgetView<CommandEventBus> implements RecipePresenter.View {

    public interface RecipeViewUiBinder extends UiBinder<Widget, RecipeViewImpl> { }

    @UiField HasClickHandlers save;
    @UiField TextBox name;
    @UiField ListBox duration;
    @UiField TextArea text;
    @UiField HasText id;
    @UiField TextBox ingredient1;
    @UiField TextBox ingredient2;
    @UiField TextBox ingredient3;
    private HasText durationHasText;
    
    @Inject
    public RecipeViewImpl(final CommandEventBus eventBus,
            RecipeViewUiBinder uiBinder) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));

        for (Duration d : Duration.values()) {
            duration.addItem(d.toString());
        }
        
        durationHasText = new HasText() {
            public void setText(String text) {
                for (int i = 0; i < duration.getItemCount(); i++) {
                    if (text != null && text.equals(duration.getValue(i))) {
                        duration.setSelectedIndex(i);
                    }
                }
            }
            
            public String getText() {
                return duration.getValue(duration.getSelectedIndex());
            }};
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
        return durationHasText;
    }

    @Override
    public HasText getIngredient1() {
        return ingredient1;
    }

    @Override
    public HasText getIngredient2() {
        return ingredient2;
    }

    @Override
    public HasText getIngredient3() {
        return ingredient3;
    }
    
    @Override
    public String getLocation() {
        return "edit";
    }

}
