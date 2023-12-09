package cz.cvut.fit.niadp.mvcgame.model.levels.manager;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;

public interface ILevelManager {

    void init();

    void nextLevel();

    List<AbsBound> getLevelBounds();

    List<AbsEnemy> getLevelEnemies();

    List<GameObject> getLevelGameObjects();

    int getCurrentLevelNumber();

    void removeEnemy(AbsEnemy enemy);
}
