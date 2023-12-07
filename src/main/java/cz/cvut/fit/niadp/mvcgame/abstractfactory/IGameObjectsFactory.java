package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public interface IGameObjectsFactory {

    AbsCannon createCannon();
    
    void setModel(IGameModel gameModel);
    
    AbsMissile createMissile(double initAngle, int initVelocity);
    // TODO enemies, gameInfo
}
