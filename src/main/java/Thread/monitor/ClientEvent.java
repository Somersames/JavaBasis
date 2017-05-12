package Thread.monitor;

import java.util.EventObject;

/**
 * Created by szh on 2017/5/12.
 */
public class ClientEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ClientEvent(Object source) {
        super(source);
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
