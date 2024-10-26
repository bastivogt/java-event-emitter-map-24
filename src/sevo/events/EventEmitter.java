package sevo.events;

import java.util.HashMap;

public class EventEmitter<E extends Event> {
    HashMap<String, IListener<E>> listsners;

    public EventEmitter() {
        this.listsners = new HashMap<String, IListener<E>>();
    }

    public static EventEmitter initialize() {
        return new EventEmitter();
    }

    public Boolean hasListener(String type) {
        return this.listsners.containsKey(type);
    }

    public Boolean on(String type, IListener<E> listener) {
        if(!this.hasListener(type)) {
            this.listsners.put(type, listener);
            return true;
        }
        return false;
    }

    public Boolean off(String type) {
        if(this.hasListener(type)) {
            this.listsners.remove(type);
            return true;
        }
        return false;
    }

    public HashMap<String, IListener<E>> getListeners() {
        return this.listsners;
    }

    public Boolean emit(E e) {
        if(this.hasListener(e.getType())) {
            this.listsners.get(e.getType()).action(e);
            return true;
        }
        return false;
    }

    public void clear() {
        this.listsners.clear();
    }
}
