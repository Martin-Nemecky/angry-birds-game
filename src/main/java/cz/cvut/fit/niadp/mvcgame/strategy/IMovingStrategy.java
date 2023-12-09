package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public interface IMovingStrategy {
    void updatePosition(AbsMissile missile);
}
