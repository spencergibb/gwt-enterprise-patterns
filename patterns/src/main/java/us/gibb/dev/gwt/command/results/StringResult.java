package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class StringResult implements Result {
    private static final long serialVersionUID = 5957802477957354219L;
    private String o;
    
    StringResult() {
    }

    public StringResult(String o) {
        this.o = o;
    }
    
    public String getString() {
        return o;
    }
}
