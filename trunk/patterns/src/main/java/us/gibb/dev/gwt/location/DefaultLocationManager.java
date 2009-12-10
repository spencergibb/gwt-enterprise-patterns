package us.gibb.dev.gwt.location;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class DefaultLocationManager implements LocationManager {

    public void changeLocation(String location, String... params) {
        History.newItem(new Location(location, params).toString(), true);
    }

    public void changeLocationIfNotCurrent(String location, String... params) {
        if (currentLocation(location) == null) { //location is not the 'current location'
            changeLocation(location, params);
        }
    }

    public Location currentLocation() {
        String token = History.getToken();
        if (token != null && !token.trim().isEmpty()) {
            return Location.fromString(token);
        }
        return null;
    }

    public Location currentLocation(String requiredLocation) {
        Location currentLocation = currentLocation();
        if (currentLocation != null && currentLocation.getValue().equals(requiredLocation)) {
            return currentLocation;
        }
        return null;
    }

    public void addValueChangeHandler(ValueChangeHandler<String> valueChangeHandler) {
        History.addValueChangeHandler(valueChangeHandler);        
    }

}
