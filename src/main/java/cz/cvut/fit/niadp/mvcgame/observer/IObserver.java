package cz.cvut.fit.niadp.mvcgame.observer;

public interface IObserver {
    <T> void update(T data);
}
