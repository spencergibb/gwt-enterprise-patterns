package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class LongResult implements Result {
    private static final long serialVersionUID = -1877901402004848760L;
    private Long o;
    
    LongResult() {
    }

    public LongResult(Long o) {
        this.o = o;
    }
    
    public Long getLong() {
        return o;
    }
}
