package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;

public interface IObserver {
    <T extends GameObject> void update(T data, AspectType type);
}
