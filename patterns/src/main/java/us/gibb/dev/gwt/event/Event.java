package us.gibb.dev.gwt.event;

@SuppressWarnings("unchecked")
public abstract class Event<T, H extends EventHandler> {

    private T value;

    public Event() {
    }

    public Event(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public Object getTypeObject() {
        return getClass();
    }

    /**
     * Implementation of abstract method in GwtEvent
     */
    protected void dispatch(H handler) {
        handler.handle(this);
    };

}
