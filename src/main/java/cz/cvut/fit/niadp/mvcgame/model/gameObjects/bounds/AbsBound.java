package cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.LifetimeUnlimitedGameObject;

public abstract class AbsBound extends LifetimeUnlimitedGameObject {
    
    @Override
    public abstract AbsBound clone();
}
