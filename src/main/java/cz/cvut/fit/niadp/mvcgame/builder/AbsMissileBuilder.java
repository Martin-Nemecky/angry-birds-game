package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public abstract class AbsMissileBuilder<T extends AbsMissile> implements IGameObjectBuilder<T> {
    
    protected T missile;

    public abstract AbsMissileBuilder<T> withPosition(Position position);

    public abstract AbsMissileBuilder<T> withAngle(double angle);

    public abstract AbsMissileBuilder<T> withVelocity(int velocity);

    public abstract MissileABuilder withMovingStrategy(IMovingStrategy strategy);

    @Override
    public T build() {
        return missile;
    }
}
