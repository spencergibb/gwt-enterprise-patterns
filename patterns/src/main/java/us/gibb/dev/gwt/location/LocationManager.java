package us.gibb.dev.gwt.location;

import com.google.gwt.event.logical.shared.ValueChangeHandler;


public interface LocationManager {

    public void changeLocation(String location, String... params);
    public void changeLocationIfNotCurrent(String location, String... params);
    public Location currentLocation();
    public Location currentLocation(String requiredLocation);
    public void addValueChangeHandler(ValueChangeHandler<String> handler);
}
