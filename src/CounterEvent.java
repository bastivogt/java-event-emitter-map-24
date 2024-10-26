import sevo.events.Event;

public class CounterEvent extends Event {

    public final static String COUNTER_STARTED = "counterStarted";
    public final static String COUNTER_CHANGED = "counterChanged";
    public final static String COUNTER_FINISHED = "counterFinished";


    CounterEvent(String type, Object sender) {
        super(type, sender);
    }
}
