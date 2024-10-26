import javax.management.monitor.CounterMonitor;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Counter c = new Counter();
        c.getEventEmitter().on(CounterEvent.COUNTER_STARTED, (CounterEvent e) -> {
            Counter sender = (Counter) e.getSender();
            System.out.println(e.getType());
            System.out.println(sender.getCount());
        });

        c.getEventEmitter().on(CounterEvent.COUNTER_CHANGED, Main::c_counterChangedHandler);

        c.getEventEmitter().on(CounterEvent.COUNTER_FINISHED, (CounterEvent e) -> {
            Counter sender = (Counter) e.getSender();
            System.out.println(e.getType());
            System.out.println(sender.getCount());
            try {
                Thread.sleep(3000);
                sender.getEventEmitter().emit(new CounterEvent("counterTimeout", sender));
            }catch (Exception ex) {
                System.out.println(ex.toString());
            }
        });

        c.getEventEmitter().on("counterTimeout", (CounterEvent e) -> {
            System.out.println(e.getType());
        });

        // c.getEventEmitter().off(CounterEvent.COUNTER_CHANGED);

        c.run();

        System.out.println(c.getEventEmitter().getListeners());
    }


    static void c_counterChangedHandler(CounterEvent e) {
        Counter sender = (Counter) e.getSender();
        System.out.println(e.getType());
        System.out.println(sender.getCount());
    }
}

