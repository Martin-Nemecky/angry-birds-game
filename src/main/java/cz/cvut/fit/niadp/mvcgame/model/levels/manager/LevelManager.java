package cz.cvut.fit.niadp.mvcgame.model.levels.manager;

import java.util.List;
import java.util.stream.Stream;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
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
        this.currentLevel = 1;
        this.factory = factory;
        levels = List.of(new Level1(factory), new Level2(factory),  new Level3(factory));
    }

    public LevelManager(int currentLevel, List<AbsLevel> levels, IGameObjectsFactory factory) {
        this.currentLevel = currentLevel;
        this.levels = levels;
        this.factory = factory;
    }

    @Override
    public void nextLevel() {
        this.currentLevel = (this.currentLevel % MvcGameConfig.LEVEL_COUNT) + 1;
        if(this.currentLevel == 1) {
            this.levels = List.of(new Level1(factory), new Level2(factory),  new Level3(factory));
        }
    }

    @Override
    public void addHurtEnemy(AbsEnemy enemy) {
        levels.get(this.currentLevel - 1).addHurtEnemy(enemy);
    }
    
    @Override
    public void removeEnemy(AbsEnemy enemy) {
        levels.get(this.currentLevel - 1).removeEnemy(enemy);
    }

    public int getCurrentLevelNumber() {
        return this.currentLevel;
    }

    public void setCurrentLevelNumber(int level) {
        this.currentLevel = level;
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
    public void setLevelEnemies(List<AbsEnemy> enemies) {
        levels.get(this.currentLevel - 1).setEnemies(enemies);
    }

    @Override
    public List<GameObject> getLevelGameObjects() {
        return Stream.concat(getLevelBounds().stream(), getLevelEnemies().stream()).toList();
    }

    @Override
    public LevelManager clone() {
        return new LevelManager(
            this.currentLevel, 
            this.levels.stream().map(l -> l.clone()).toList(),
            this.factory
        );
    }
}
