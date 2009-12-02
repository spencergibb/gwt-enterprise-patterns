package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class ShortResult implements Result {
    private static final long serialVersionUID = -4821444944931589327L;
    private Short o;
    
    ShortResult() {
    }

    public ShortResult(Short o) {
        this.o = o;
    }
    
    public Short getShort() {
        return o;
    }
}
