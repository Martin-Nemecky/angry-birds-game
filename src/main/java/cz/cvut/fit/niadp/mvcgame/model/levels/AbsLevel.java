package cz.cvut.fit.niadp.mvcgame.model.levels;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;

public abstract class AbsLevel {

    protected final IGameObjectsFactory gameObjectsFactory;
    protected final List<AbsBound> bounds; 
    protected final List<AbsEnemy> enemies;

    public AbsLevel(IGameObjectsFactory factory) {
        this.gameObjectsFactory = factory;
        bounds = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public abstract void init();

    public void removeEnemy(AbsEnemy enemy) {
        this.enemies.remove(enemy);
    }
    
    public List<AbsBound> getBounds() {
        return this.bounds;
    }

    public List<AbsEnemy> getEnemies() {
        return this.enemies;
    } 
}
