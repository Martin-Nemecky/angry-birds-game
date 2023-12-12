package cz.cvut.fit.niadp.mvcgame.abstract_factory;

import cz.cvut.fit.niadp.mvcgame.builder.AbsMissileBuilder;
import cz.cvut.fit.niadp.mvcgame.builder.MissileABuilder;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.BoundA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions.CollisionA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.EnemyA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.info.AbsGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.info.SimpleGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.MissileA;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class GameObjectsFactoryA implements IGameObjectsFactory {
    
    private IGameModel model;
    private static IGameObjectsFactory instance;

    private GameObjectsFactoryA() {}

    public static IGameObjectsFactory getInstance() {
        if(instance == null) {
            instance = new GameObjectsFactoryA();
        }
        
        return instance;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }


    @Override
    public MissileA createMissile(double initAngle, int initVelocity) {
        MissileABuilder builder = new MissileABuilder();
        return builder
            .withPosition(new Position(this.model.getCannonPosition().getX(), this.model.getCannonPosition().getY()))
            .withAngle(initAngle)
            .withVelocity(initVelocity)
            .withMovingStrategy(this.model.getMovingStrategy())
            .build();
    }

    @Override
    public AbsMissile createMissile(Position pos, double angle, int velocity, IMovingStrategy strategy) {
        MissileABuilder builder = new MissileABuilder();
        return builder
            .withPosition(pos)
            .withAngle(angle)
            .withVelocity(velocity)
            .withMovingStrategy(strategy)
            .build();
    }

    @Override
    public AbsEnemy createEnemy(Position position, boolean isHurt) {
        return new EnemyA(position, isHurt);
    }

    @Override
    public AbsBound createBound(Position position) {
        return new BoundA(position);
    }

    @Override
    public AbsGameInfo createGameInfo() {
        return new SimpleGameInfo();
    }

    @Override
    public AbsCollision createCollision(Position position) {
        return new CollisionA(position);
    }

    @Override
    public void setModel(IGameModel gameModel){
        this.model = gameModel;
    }
}
