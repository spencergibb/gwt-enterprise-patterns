package us.gibb.dev.gwt.command.results;

import us.gibb.dev.gwt.command.Result;


public class ByteResult implements Result {
    private static final long serialVersionUID = 3483484603825599941L;
    private Byte o;
    
    ByteResult() {
    }

    public ByteResult(Byte o) {
        this.o = o;
    }
    
    public Byte getByte() {
        return o;
    }
}
