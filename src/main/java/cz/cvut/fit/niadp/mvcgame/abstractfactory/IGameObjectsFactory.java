package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.info.AbsGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public interface IGameObjectsFactory {

    AbsCannon createCannon();
    
    void setModel(IGameModel gameModel);
    
    AbsMissile createMissile(double initAngle, int initVelocity);

    AbsEnemy createEnemy(Position position);

    AbsCollision createCollision(Position position);

    AbsBound createBound(Position position);

    AbsGameInfo createGameInfo();
}
