package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public interface IObserver {
    <T extends GameObject> void update(T data);
}
