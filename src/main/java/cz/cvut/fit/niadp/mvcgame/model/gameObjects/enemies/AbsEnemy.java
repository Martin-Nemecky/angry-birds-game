package cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.LifetimeLimitedGameObject;

public abstract class AbsEnemy extends LifetimeLimitedGameObject {

    protected boolean isHurt;

    protected AbsEnemy(Position position) {
        super(position);
    }

    protected AbsEnemy(Position position, boolean isHurt) {
        super(position);
        this.isHurt = isHurt;
    }

    public void setIsHurt(boolean isHurt) {
        this.isHurt = isHurt;
    }

    public boolean isHurt() {
        return this.isHurt;
    }

    @Override
    public abstract AbsEnemy clone();
}
