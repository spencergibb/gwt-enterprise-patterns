package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class BooleanResult implements Result {
    private static final long serialVersionUID = -5112476861008167390L;
    private Boolean o;
    
    BooleanResult() {
    }

    public BooleanResult(Boolean o) {
        this.o = o;
    }
    
    public Boolean getBoolean() {
        return o;
    }
}
