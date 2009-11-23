package us.gibb.dev.gwt.demo.client;

import us.gibb.dev.gwt.command.Result;

public class HelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private String hello;

    HelloResult() {
    }
    
    public HelloResult(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

}
