package sevo.events;

import java.util.HashMap;

public class EventEmitter<E extends Event> {
    HashMap<String, IListener<E>> listeners;

    public EventEmitter() {
        this.listeners = new HashMap<>();
    }

    public static <T extends Event> EventEmitter<T> initialize() {
        return new EventEmitter<T>();
    }

    public Boolean hasListener(String type) {
        return this.listeners.containsKey(type);
    }

    public Boolean on(String type, IListener<E> listener) {
        if(!this.hasListener(type)) {
            this.listeners.put(type, listener);
            return true;
        }
        return false;
    }

    public Boolean off(String type) {
        if(this.hasListener(type)) {
            this.listeners.remove(type);
            return true;
        }
        return false;
    }

    public HashMap<String, IListener<E>> getListeners() {
        return this.listeners;
    }

    public Boolean emit(E e) {
        if(this.hasListener(e.getType())) {
            this.listeners.get(e.getType()).action(e);
            return true;
        }
        return false;
    }

    public void clear() {
        this.listeners.clear();
    }
}
