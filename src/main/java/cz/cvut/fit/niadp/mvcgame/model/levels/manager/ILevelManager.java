package cz.cvut.fit.niadp.mvcgame.model.levels.manager;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;

public interface ILevelManager {

    void nextLevel();

    void addHurtEnemy(AbsEnemy hurtEnemy);
    void removeEnemy(AbsEnemy enemy);

    int getCurrentLevelNumber();

    void setCurrentLevelNumber(int level);

    List<AbsBound> getLevelBounds();

    List<AbsEnemy> getLevelEnemies();

    void setLevelEnemies(List<AbsEnemy> enemies);

    List<GameObject> getLevelGameObjects();

    ILevelManager clone();
}
