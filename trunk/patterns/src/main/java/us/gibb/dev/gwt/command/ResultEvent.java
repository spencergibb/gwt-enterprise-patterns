package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.event.EventHandler;

public class ResultEvent<R extends Result> extends Event<R, ResultEvent.Handler<R>> {
    private static final long serialVersionUID = -710409787744632889L;

    public abstract static class Handler<R extends Result> extends EventHandler<ResultEvent<R>> {
        private Class<? extends Result> typeClass;

        public Handler(Class<R> typeClass) {
            this.typeClass = typeClass;
        }

        @Override
        public Class<?> getTypeClass() {
            return typeClass;
        }
        
    }

    public ResultEvent(R result) {
        super(result);
    }
    
    public R getResult() {
        return getData();
    }

    @Override
    public Class<?> getTypeClass() {
        return getResult().getClass();
    }
}
