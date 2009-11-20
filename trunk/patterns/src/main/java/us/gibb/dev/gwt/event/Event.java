package us.gibb.dev.gwt.event;

@SuppressWarnings("unchecked")
public abstract class Event<T, H extends EventHandler> {

    private T data;

    public Event() {
    }

    public Event(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Class<?> getTypeClass() {
        return getClass();
    }

    /**
     * Implementation of abstract method in GwtEvent
     */
    protected void dispatch(H handler) {
        handler.handle(this);
    };

}
