package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.location.LocationManager;


public class TestResult implements Result {
    private static final long serialVersionUID = -2937007916030452783L;
    
    public static void main(String[] args) {
        CommandEventBus bus = new DefaultCommandEventBus(new LocationManager());
        bus.add(new ResultEvent.Handler<TestResult>(TestResult.class) {
            public void handle(ResultEvent<TestResult> event) {
                event.getResult();
            }});

        bus.fire(new TestResult());
    }
}
