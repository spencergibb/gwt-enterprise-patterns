package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Hello;

public class HelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private Hello model;

    HelloResult() {
    }
    
    public HelloResult(Hello model) {
        this.model = model;
    }

    public Hello getHello() {
        return model;
    }

}
