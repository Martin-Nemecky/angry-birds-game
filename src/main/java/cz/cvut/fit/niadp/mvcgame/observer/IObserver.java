package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;

public interface IObserver {
    void update(AspectType type);
}
