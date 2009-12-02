package us.gibb.dev.gwt.command.results;

import java.util.Date;

import us.gibb.dev.gwt.command.Result;


public class DateResult implements Result {
    private static final long serialVersionUID = -2837056494983701122L;
    private Date o;
    
    DateResult() {
    }

    public DateResult(Date o) {
        this.o = o;
    }
    
    public Date getDate() {
        return o;
    }
}
