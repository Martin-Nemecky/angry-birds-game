package cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.LifetimeLimitedGameObject;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private double initAngle;
    private int initVelocity;

    protected AbsMissile(Position initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    public abstract void move();

    public double getInitAngle() {
        return this.initAngle;
    }

    public void setInitAngle(double angle) {
        this.initAngle = angle;
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public void setInitVelocity(int velocity) {
        this.initVelocity = velocity;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitMissile(this);
    }
}
