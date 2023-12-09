package cz.cvut.fit.niadp.mvcgame.model.levels.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.levels.AbsLevel;
import cz.cvut.fit.niadp.mvcgame.model.levels.Level1;
import cz.cvut.fit.niadp.mvcgame.model.levels.Level2;
import cz.cvut.fit.niadp.mvcgame.model.levels.Level3;

public class LevelManager implements ILevelManager {
    
    private int currentLevel;

    private List<AbsLevel> levels;

    private IGameObjectsFactory factory;

    public LevelManager(IGameObjectsFactory factory) {
        this.currentLevel = 3;
        levels = new ArrayList<>();
        this.factory = factory;
    }

    public void init() {
        Level1 level1 = new Level1(factory);
        level1.init();
        Level2 level2 = new Level2(factory);
        level2.init();
        Level3 level3 = new Level3(factory);
        level3.init();

        levels.addAll(List.of(
            level1, level2, level3
        ));
    }
    
    public int getCurrentLevelNumber() {
        return this.currentLevel;
    }

    @Override
    public void nextLevel() {
        this.currentLevel = (this.currentLevel % this.levels.size()) + 1;
        if(this.currentLevel == 1) {
            this.levels.clear();
            init();
        }
    }

    @Override
    public List<AbsBound> getLevelBounds() {
        return levels.get(this.currentLevel - 1).getBounds();
    }

    @Override
    public List<AbsEnemy> getLevelEnemies() {
        return levels.get(this.currentLevel - 1).getEnemies();
    }

    @Override
    public List<GameObject> getLevelGameObjects() {
        return Stream.concat(getLevelBounds().stream(), getLevelEnemies().stream()).toList();
    }

    @Override
    public void removeEnemy(AbsEnemy enemy) {
        levels.get(this.currentLevel - 1).removeEnemy(enemy);
    }
}
