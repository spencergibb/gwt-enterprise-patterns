package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.location.LocationManager;

public class TestCommand implements Command<TestResult> {
    private static final long serialVersionUID = -3937874158924353940L;
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        CommandEventBus bus = new DefaultCommandEventBus(new LocationManager());
        bus.add(new CommandEvent.Handler<TestCommand>(TestCommand.class) {
            public void handle(CommandEvent<TestCommand> event) {
                event.getCommand();
            }});

        bus.add(new CommandEvent.Handler(TestCommand.class) {
            public void handle(Event event) {
            }});

        bus.fire(new TestCommand());
    }
}