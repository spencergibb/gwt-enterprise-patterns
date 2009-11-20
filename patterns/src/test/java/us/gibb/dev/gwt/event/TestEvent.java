package us.gibb.dev.gwt.event;

/**
 * Event
 * EventHandler<Event>
 * Type<Handler>
 */
public class TestEvent extends Event<String, TestEvent.Handler> {
    
    public static abstract class Handler extends EventHandler<TestEvent> {}

    public static final Type<Handler> TYPE = new Type<Handler>();

    public TestEvent(String data) {
        super(data);
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

}