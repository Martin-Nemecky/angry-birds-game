package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.specific.SimpleAspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class GameModel implements IObservable {

    private final AbsCannon cannon;
    private final Map<AspectType, Set<IObserver>> observers;
    private IGameObjectsFactory gameObjectsFactory;
    private final List<AbsMissile> missiles;

    public GameModel() {
        this.observers = new HashMap<>();
        for(AspectType aspectType : AspectType.values()){
            this.observers.put(aspectType, new HashSet<>());
        }
        this.gameObjectsFactory = GameObjectsFactoryA.getInstance();
        this.gameObjectsFactory.setModel(this);
        this.cannon = this.gameObjectsFactory.createCannon();
        this.missiles = new ArrayList<>();
    }

    public void update() {
        this.moveMissiles();
    }

    private void moveMissiles() {
        this.missiles.forEach(missile -> missile.move(new Vector(MvcGameConfig.MOVE_STEP, 0)));
        this.destroyMissiles();
        this.missiles.forEach(missile -> this.notifyObservers(new SimpleAspect(AspectType.MISSILE_MOVED, missile)));
    }

    private void destroyMissiles() {
        this.missiles.removeAll(
            this.missiles.stream().filter(missile -> missile.getPosition().getX() > MvcGameConfig.MAX_X).toList()
        );
    }

    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, cannon));
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, cannon));
    }

    public void cannonShoot() {
        AbsMissile m = this.cannon.shoot();
        this.missiles.add(m);
        this.notifyObservers(new SimpleAspect(AspectType.MISSILE_FIRED, m));
    }
        

    @Override
    public void registerObserver(IObserver observer, AspectType aspects[]) {
        for(AspectType aspect : aspects){
            this.observers.get(aspect).add(observer);
        }        
    }

    @Override
    public void unregisterObserver(IObserver observer, AspectType aspect) {
        this.observers.get(aspect).remove(observer);
    }

    @Override
    public <T extends GameObject> void notifyObservers(IAspect<T> aspect) {
        this.observers.get(aspect.getAspectType()).forEach(observer -> observer.update(aspect.getData()));
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
    }

    public AbsCannon getCannon() {
        return this.cannon;
    }

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.of(this.cannon), this.missiles.stream()).toList();
    }
}
