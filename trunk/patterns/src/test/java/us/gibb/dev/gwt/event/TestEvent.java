package us.gibb.dev.gwt.event;

import us.gibb.dev.gwt.location.LocationManager;


/**
 * Event
 * EventHandler<Event>
 * Type<Handler>
 */
public class TestEvent extends Event<String, TestEvent.Handler> {
    
    public static abstract class Handler extends EventHandler<TestEvent> {
        public Object getTypeObject() {
            return TestEvent.class;
        }
    }

    public TestEvent(String data) {
        super(data);
    }

    public static void main(String[] args) {
        EventBus bus = new DefaultEventBus(new LocationManager());
        bus.add(new Handler() {
            public void handle(TestEvent event) {
            }});
        bus.fire(new TestEvent("testing"));
    }
}