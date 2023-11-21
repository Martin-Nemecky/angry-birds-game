package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;

public interface IObservable {
    void registerObserver(IObserver observer, AspectType aspects[]);
    void unregisterObserver(IObserver observer, AspectType aspect);
    <T extends GameObject> void notifyObservers(IAspect<T> aspect);
}
