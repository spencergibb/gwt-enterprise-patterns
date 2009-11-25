package us.gibb.dev.gwt.command.inject;

import us.gibb.dev.gwt.command.DefaultCommandEventBus;
import us.gibb.dev.gwt.location.LocationManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DefaultCommandEventBusProvider implements Provider<DefaultCommandEventBus> {
    
    private LocationManager locationManager;

    @Inject
    public DefaultCommandEventBusProvider(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @Override
    public DefaultCommandEventBus get() {
        return new DefaultCommandEventBus(locationManager);
    }

}
