package sevo.events;

public interface IListener<E extends Event> {
    void action(E event);
}
