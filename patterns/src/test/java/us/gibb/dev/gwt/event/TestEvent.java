package us.gibb.dev.gwt.event;


/**
 * Event
 * EventHandler<Event>
 * Type<Handler>
 */
public class TestEvent extends Event<String, TestEvent.Handler> {
    
    public static abstract class Handler extends EventHandler<TestEvent> {
        public Class<TestEvent> getTypeClass() {
            return TestEvent.class;
        }
    }

    public TestEvent(String data) {
        super(data);
    }

    public static void main(String[] args) {
        EventBus bus = new DefaultEventBus();
        bus.add(new Handler() {
            public void handle(TestEvent event) {
            }});
        bus.fire(new TestEvent("testing"));
    }
}