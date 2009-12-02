package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class DoubleResult implements Result {
    private static final long serialVersionUID = 1703069396273548744L;
    private Double o;
    
    DoubleResult() {
    }

    public DoubleResult(Double o) {
        this.o = o;
    }
    
    public Double getDouble() {
        return o;
    }
}
