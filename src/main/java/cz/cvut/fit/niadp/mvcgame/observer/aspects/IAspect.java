package cz.cvut.fit.niadp.mvcgame.observer.aspects;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public interface IAspect<T extends GameObject> {
    
    AspectType getAspectType();
    
    T getData();
}
