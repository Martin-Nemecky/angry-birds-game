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

    public AbsLevel(List<AbsBound> bounds, List<AbsEnemy> enemies, IGameObjectsFactory factory){
        this.bounds = bounds;
        this.enemies = enemies;
        this.gameObjectsFactory = factory;
    }

    public void addHurtEnemy(AbsEnemy enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemy(AbsEnemy enemy) {
        this.enemies.removeIf(e -> e.getPosition().getX() == enemy.getPosition().getX() && e.getPosition().getY() == enemy.getPosition().getY());
    }
    
    public List<AbsBound> getBounds() {
        return this.bounds.stream().map(b -> b.clone()).toList();
    }

    public List<AbsEnemy> getEnemies() {
        return this.enemies.stream().map(e -> e.clone()).toList();
    } 
}