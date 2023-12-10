package cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.LifetimeLimitedGameObject;

public abstract class AbsCollision extends LifetimeLimitedGameObject {

    protected AbsCollision(Position position) {
        super(position);
    }
}
