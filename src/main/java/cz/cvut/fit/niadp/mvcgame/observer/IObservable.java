package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;

public interface IObservable {
    void registerObserver(IObserver observer, AspectType aspects[]);
    void unregisterObserver(IObserver observer, AspectType aspect);
    <T> void notifyObservers(IAspect<T> aspect);
}
