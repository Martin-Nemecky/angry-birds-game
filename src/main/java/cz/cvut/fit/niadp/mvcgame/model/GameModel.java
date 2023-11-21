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
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

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
    private IMovingStrategy movingStrategy;

    public GameModel() {
        this.observers = new HashMap<>();
        for(AspectType aspectType : AspectType.values()){
            this.observers.put(aspectType, new HashSet<>());
        }
        this.gameObjectsFactory = GameObjectsFactoryA.getInstance();
        this.gameObjectsFactory.setModel(this);
        this.cannon = this.gameObjectsFactory.createCannon();
        this.missiles = new ArrayList<>();
        this.movingStrategy = new SimpleMovingStrategy();
    }

    public void update() {
        this.moveMissiles();
    }

    private void moveMissiles() {
        this.missiles.forEach(AbsMissile::move);
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
        List<AbsMissile> missiles = this.cannon.shoot();
        this.missiles.addAll(missiles);
        this.notifyObservers(new SimpleAspect(AspectType.MISSILE_FIRED, missiles.get(0)));
    }
    
    @Override
    public void registerObserver(IObserver observer, AspectType aspects[]) {
        for(AspectType aspect : aspects){
            this.observers.get(aspect).add(observer);
        }    
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
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

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (this.movingStrategy instanceof SimpleMovingStrategy) {
            this.movingStrategy = new RealisticMovingStrategy();
        }
        else if (this.movingStrategy instanceof RealisticMovingStrategy) {
            this.movingStrategy = new SimpleMovingStrategy();
        }
        else {

        }
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;
        // game model snapshot (enemies, gameInfo, strategy, state)
    }

    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = this.getCannonPosition().getX();
        gameModelSnapshot.cannonPositionY = this.getCannonPosition().getY();
        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        this.cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        this.cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
    }
}
