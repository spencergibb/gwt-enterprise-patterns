package us.gibb.dev.gwt.demo.client.command;

import us.gibb.dev.gwt.command.Result;
import us.gibb.dev.gwt.demo.model.vo.HelloVO;

public class HelloResult implements Result {
    private static final long serialVersionUID = -5277167904350114991L;
    private HelloVO hello;

    HelloResult() {
    }
    
    public HelloResult(HelloVO hello) {
        this.hello = hello;
    }

    public HelloVO getHello() {
        return hello;
    }

}
