package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class FloatResult implements Result {
    private static final long serialVersionUID = 2521339601027279407L;
    private Float o;
    
    FloatResult() {
    }

    public FloatResult(Float o) {
        this.o = o;
    }
    
    public Float getFloat() {
        return o;
    }
}
