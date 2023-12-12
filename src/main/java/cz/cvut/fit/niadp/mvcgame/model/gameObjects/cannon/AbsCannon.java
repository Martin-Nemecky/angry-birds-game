package cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected static IShootingMode DYNAMIC_SHOOTING_MODE = new DynamicShootingMode();

    protected int power;
    protected double angle;
    protected int batchSize;

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void aimUp();
    public abstract void aimDown();
    public abstract void powerUp();
    public abstract void powerDown();
    public abstract void increaseBatch();
    public abstract void decreaseBatch();

    public abstract void primitiveShoot();
    public abstract List<AbsMissile> shoot();

    public abstract void toggleShootingMode();

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCannon(this);
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public double getAngle() {
        return this.angle;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public abstract AbsCannon clone();
}
