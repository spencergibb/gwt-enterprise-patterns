package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Hello;

public class HelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private Hello hello;

    HelloResult() {
    }
    
    public HelloResult(Hello hello) {
        this.hello = hello;
    }

    public void setHello(Hello hello) {
        this.hello = hello;
    }

    public Hello getHello() {
        return hello;
    }

}
