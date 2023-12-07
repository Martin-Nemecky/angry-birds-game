package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.MissileA;

public class GameObjectsFactoryA implements IGameObjectsFactory {
    
    private GameModel model;
    
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
    public void setModel(GameModel gameModel){
        this.model = gameModel;
    }
}
