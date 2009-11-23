package us.gibb.dev.gwt.event;

public class FailureEvent extends Event<String, FailureEvent.Handler> {
    
    public static abstract class Handler extends EventHandler<FailureEvent> {
        public Class<FailureEvent> getTypeClass() {
            return FailureEvent.class;
        }
    }

    private Throwable throwable;

    public FailureEvent(String msg) {
        super(msg);
    }

    public FailureEvent(Throwable t) {
        this.throwable = t;
    }

    public FailureEvent(String msg, Throwable t) {
        this.throwable = t;
    }
    
    public String getMessage() {
        return getData();
    }
    
    public Throwable getThrowable() {
        return throwable;
    }
}