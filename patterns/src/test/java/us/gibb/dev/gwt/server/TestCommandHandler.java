package us.gibb.dev.gwt.server;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.command.TestCommand;
import us.gibb.dev.gwt.command.TestResult;
import us.gibb.dev.gwt.server.command.handler.AbstractCommandHandler;
import us.gibb.dev.gwt.server.command.handler.Context;
import us.gibb.dev.gwt.server.command.handler.DefaultCommandHandlerRegistry;
import us.gibb.dev.gwt.server.inject.DispatchModule;

import com.google.inject.Guice;
import com.google.inject.Singleton;

@Singleton
public class TestCommandHandler extends AbstractCommandHandler<TestCommand, TestResult> {

    @Override
    public TestResult execute(TestCommand command, Context context) throws CommandException {
        return new TestResult();
    }

    public static void main(String[] args) {
        DefaultCommandHandlerRegistry registry = new DefaultCommandHandlerRegistry();
        registry.add(new TestCommandHandler());

        Guice.createInjector(new DispatchModule(){
        protected void configureDispatch() {
            scan(TestCommandHandler.class.getPackage());
        }});
    }

}
