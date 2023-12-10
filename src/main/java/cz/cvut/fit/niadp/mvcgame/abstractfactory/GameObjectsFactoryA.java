package cz.cvut.fit.niadp.mvcgame.abstractfactory;

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
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.MissileA;

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
        return new MissileA(
                new Position(this.model.getCannonPosition().getX(), this.model.getCannonPosition().getY()),
                initAngle,
                initVelocity,
                this.model.getMovingStrategy()
        );
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
