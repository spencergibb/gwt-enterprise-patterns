package us.gibb.dev.gwt.command;

import us.gibb.dev.gwt.event.Event;
import us.gibb.dev.gwt.event.EventHandler;

public class ResultEvent<R extends Result> extends Event<R, ResultEvent.Handler<R>> {
    private static final long serialVersionUID = -710409787744632889L;
    private static final String TYPE_PREFIX = Result.class.getName()+"::";

    public abstract static class Handler<R extends Result> extends EventHandler<ResultEvent<R>> {
        private Class<? extends Command<R>> typeClass;

        public Handler(Class<? extends Command<R>> commandClass) {
            this.typeClass = commandClass;
        }

        @Override
        public Object getTypeObject() {
            return createTypeObject(typeClass);
        }
        
    }

    private Class<? extends Command<R>> commandClass;
    public ResultEvent(Class<? extends Command<R>> commandClass, R result) {
        super(result);
        this.commandClass = commandClass;
    }
    
    public R getResult() {
        return getValue();
    }

    @Override
    public Object getTypeObject() {
        return createTypeObject(commandClass);
    }

    private static String createTypeObject(Class<? extends Command<?>> clazz) {
        return TYPE_PREFIX+clazz;
    }
}
