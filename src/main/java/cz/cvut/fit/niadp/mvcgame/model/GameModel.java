package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.Cannon;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.specific.PositionChangeAspect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameModel implements IObservable {

    private final Cannon cannon;
    private final Map<AspectType, Set<IObserver>> observers;

    public GameModel() {
        this.cannon = new Cannon(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y));
        this.observers = new HashMap<>();
        
        for(AspectType aspect : AspectType.values()){
            this.observers.put(aspect, new HashSet<IObserver>());
        }
    }

    public void update() {
        // this.moveMissiles();
    }

    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers(new PositionChangeAspect(getCannonPosition()));
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers(new PositionChangeAspect(getCannonPosition()));
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
    public <T> void notifyObservers(IAspect<T> aspect) {
        this.observers.get(aspect.getAspectType()).forEach(observer -> observer.update(aspect.getData()));
    }
}
