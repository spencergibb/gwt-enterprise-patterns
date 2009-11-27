package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Hello;

public class HelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private String hello;
    private Hello model;

    HelloResult() {
    }
    
    public HelloResult(String hello) {
        this.hello = hello;
    }

    public HelloResult(String hello, Hello model) {
        this.hello = hello;
        this.model = model;
    }

    public String getHello() {
        return hello;
    }
    
    public Hello getModel() {
        return model;
    }

}
