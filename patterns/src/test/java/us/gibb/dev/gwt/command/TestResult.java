package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.location.LocationManager;


public class TestResult implements Result {
    private static final long serialVersionUID = -2937007916030452783L;
    
    public static void main(String[] args) {
        CommandEventBus bus = new DefaultCommandEventBus(new LocationManager());
        bus.add(new ResultEvent.Handler<TestResult>(TestCommand.class) {
            public void handle(ResultEvent<TestResult> event) {
                TestResult result = event.getResult();
                result.toString();
            }});

        bus.fire(new TestCommand(), new TestResult());
    }
    
    public static class TestCommand2 implements Command<TestResult2> {
        private static final long serialVersionUID = 1L;
    }
    
    public static class TestResult2 implements Result {
        private static final long serialVersionUID = 1L;
    }
}
