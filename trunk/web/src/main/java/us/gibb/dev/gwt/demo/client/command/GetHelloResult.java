package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.Hello;

public class GetHelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private Hello model;

    GetHelloResult() {
    }
    
    public GetHelloResult(Hello model) {
        this.model = model;
    }

    public Hello getHello() {
        return model;
    }

}
