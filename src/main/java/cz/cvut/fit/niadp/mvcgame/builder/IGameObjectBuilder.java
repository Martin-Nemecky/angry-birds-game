package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public interface IGameObjectBuilder<T extends GameObject> {
    
    T build();

}
