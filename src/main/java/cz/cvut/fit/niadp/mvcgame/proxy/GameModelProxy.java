package cz.cvut.fit.niadp.mvcgame.proxy;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public class GameModelProxy implements IGameModel {

    private IGameModel subject;
    public GameModelProxy(IGameModel model) {
        this.subject = model;
    }

    @Override
    public void registerObserver(IObserver observer, AspectType[] aspects) {
        this.subject.registerObserver(observer, aspects);
    }

    @Override
    public void unregisterObserver(IObserver observer, AspectType aspect) {
        this.subject.unregisterObserver(observer, aspect);
    }

    @Override
    public <T extends GameObject> void notifyObservers(IAspect<T> aspect) {
        this.subject.notifyObservers(aspect);
    }

    @Override
    public AbsCannon getCannon() {
        return this.subject.getCannon();
    }

    @Override
    public void increaseCannonBatch() {
        this.subject.increaseCannonBatch();
    }

    @Override
    public void decreaseCannonBatch() {
        this.subject.decreaseCannonBatch();
    }

    @Override
    public void update() {
        this.subject.update();
    }

    @Override
    public Position getCannonPosition() {
        return this.subject.getCannonPosition();
    }

    @Override
    public void moveCannonUp() {
        this.subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        this.subject.moveCannonDown();
    }

    @Override
    public void aimCannonUp() {
        this.subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        this.subject.aimCannonDown();
    }

    @Override
    public void cannonPowerUp() {
        this.subject.cannonPowerUp();
    }

    @Override
    public void cannonPowerDown() {
        this.subject.cannonPowerDown();
    }

    @Override
    public void cannonShoot() {
        this.subject.cannonShoot();
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return this.subject.getMissiles();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return this.subject.getGameObjects();
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.subject.getMovingStrategy();
    }

    @Override
    public void toggleMovingStrategy() {
        this.subject.toggleMovingStrategy();
    }

    @Override
    public void toggleShootingMode() {
        this.subject.toggleShootingMode();
    }

    @Override
    public Object createMemento() {
        return this.subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        this.subject.setMemento(memento);
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.subject.registerCommand(command);
    }

    @Override
    public void undoLastCommand() {
        this.subject.undoLastCommand();
    }
}
