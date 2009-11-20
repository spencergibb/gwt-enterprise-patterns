package us.gibb.dev.gwt.event;

public class TestIdeas {

    public static void main(String[] args) {
        EB eventBus = new EB();
        eventBus.add(CommandA.class, new CmdEvent.Handler<CommandA>() {
        });
        eventBus.add(EventA.class, new EventA.Handler() {
        });
    }

    public static class EB {
        public <E extends Evnt<?>> void add(Class<E> clazz, Hndlr<E> handler) {}
        public <C extends Cmd> void add(Class<C> clazz, CmdEvent.Handler<C> handler) {}
    }

    public abstract static class Hndlr<E extends Evnt<?>> {
        
    }
    public abstract static class Evnt<H extends Hndlr<?>> {
        
    }
    public static class EventA extends Evnt<EventA.Handler> {
        public abstract static class Handler extends Hndlr<EventA>{
            
        }
    }
    
    public static class CmdEvent<C extends Cmd> extends Evnt<CmdEvent.Handler<C>> {
        public abstract static class Handler<C extends Cmd> extends Hndlr<CmdEvent<C>>{
            
        }

    }

    public static class Cmd {
        
    }
    public static class CommandA extends Cmd {

    }
}
