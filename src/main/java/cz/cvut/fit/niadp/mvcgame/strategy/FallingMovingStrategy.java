package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public class FallingMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = MvcGameConfig.INIT_POWER;
        long time = (long) (missile.getAge() / 500.0);
        int dY = (int) (initVelocity * time);

        missile.move(new Vector(0, dY));
    }
    
}
