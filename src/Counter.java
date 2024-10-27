import sevo.events.EventEmitter;

public class Counter {




    private final EventEmitter<CounterEvent> eventEmitter;

    private int start;
    private int stop;
    private int step;
    private int count;

    public Counter(int start, int stop, int step) {
        //this.eventEmitter = new EventEmitter<CounterEvent>();
        this.eventEmitter = EventEmitter.initialize();
        this.start = start;
        this.stop = stop;
        this.step = step;
        this.count = this.start;
    }

    public Counter(int start, int stop) {
        this(start, stop, 1);
    }

    public Counter(int start) {
        this(start, 10, 1);
    }

    public Counter() {
        this(0, 10, 1);
    }


    public void run() {
        this.count = this.start;
        //System.out.println("START");
        this.eventEmitter.emit(new CounterEvent(CounterEvent.COUNTER_STARTED, this));
        for(; this.count < this.stop; this.count += this.step) {
            //System.out.println("CHANGE");
            this.eventEmitter.emit(new CounterEvent(CounterEvent.COUNTER_CHANGED, this));
        }
        //System.out.println("FINISH");
        this.eventEmitter.emit(new CounterEvent(CounterEvent.COUNTER_FINISHED, this));
    }

    public EventEmitter<CounterEvent> getEventEmitter() {
        return this.eventEmitter;
    }

    public int getCount() {
        return this.count;
    }
}
