package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.MissileA;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class MissileABuilder extends AbsMissileBuilder<MissileA> {

    public MissileABuilder() {
        this.missile = new MissileA(new Position(0, 0), 0, 10, new SimpleMovingStrategy());
    }

    @Override
    public AbsMissileBuilder<MissileA> withPosition(Position position) {
        missile.setPosition(position);
        return this;
    }

    @Override
    public AbsMissileBuilder<MissileA> withVelocity(int velocity) {
        missile.setInitVelocity(velocity);
        return this;
    }

    @Override
    public MissileABuilder withAngle(double angle) {
        missile.setInitAngle(angle);
        return this;
    }

    @Override
    public MissileABuilder withMovingStrategy(IMovingStrategy strategy){
        missile.setMovingStrategy(strategy);
        return this;
    }
    
}
