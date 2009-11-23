package us.gibb.dev.gwt.event;

import java.util.Arrays;

public class LocationChangedEvent extends Event<String, LocationChangedEvent.Handler> {
    private static final String SEP = "/";
    
    public static abstract class Handler extends EventHandler<LocationChangedEvent> {
        private String location;
        
        public Handler(String location) {
            this.location = location;
        }

        public Object getTypeObject() {
            return LocationChangedEvent.getTypeObject(location);
        }
    }

    private String[] params;

    public LocationChangedEvent(String location, String... params) {
        super(location);
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }
    
    public String getParam(int idx) {
        if (params == null || idx > params.length - 1) {
            return null;
        }
        return params[idx];
    }
    
    @Override
    public Object getTypeObject() {
        return getTypeObject(getValue());
    }

    private static String getTypeObject(String location) {
        return LocationChangedEvent.class+SEP+location;
    }
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(getValue());
        if (params != null) {
            for (String param : params) {
                out.append(SEP);
                out.append(param);
            }
        }
        return out.toString();
    }

    public static LocationChangedEvent fromString(String locationString) {
        if (locationString == null) {
            throw new RuntimeException("Missing location: "+locationString);
        }
        String trimmed = locationString.trim();
        if (trimmed.isEmpty()) {
            throw new RuntimeException("Missing location: "+locationString);
        }
        String[] split = trimmed.split(SEP);
        if (split == null || split.length == 0 || split[0].isEmpty()) {
            throw new RuntimeException("Missing location: "+locationString);
        }
        String[] params = null;
        if (split.length > 1) {
            params = Arrays.asList(split).subList(1, split.length).toArray(new String[]{});
        }
        return new LocationChangedEvent(split[0], params);
    }

}