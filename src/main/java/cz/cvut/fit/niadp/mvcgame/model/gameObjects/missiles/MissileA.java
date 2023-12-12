package cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {

    private IMovingStrategy movingStrategy;

    public MissileA(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initVelocity);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        this.movingStrategy.updatePosition(this);
    }

    public void setMovingStrategy(IMovingStrategy strategy) {
        this.movingStrategy = strategy;
    }

    @Override
    public MissileA clone() {
        return new MissileA(new Position(this.position.getX(), this.position.getY()), getInitAngle(), getInitVelocity(), movingStrategy);
    }
}
