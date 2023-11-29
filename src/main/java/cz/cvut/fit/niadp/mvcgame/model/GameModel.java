package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class GameModel implements IGameModel {

    private final AbsCannon cannon;
    private final Set<IObserver> observers;
    private IGameObjectsFactory gameObjectsFactory;
    private final List<AbsMissile> missiles;
    private IMovingStrategy movingStrategy;

    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        this.observers = new HashSet<>();
        this.gameObjectsFactory = new GameObjectsFactoryA(this);
        this.cannon = this.gameObjectsFactory.createCannon();
        this.missiles = new ArrayList<>();
        this.movingStrategy = new SimpleMovingStrategy();
        this.unexecutedCommands = new LinkedBlockingQueue<>();
        this.executedCommands = new Stack<>();
    }

    public void update() {
        this.executeCommands();
        this.moveMissiles();
    }

    private void executeCommands() {
        while(!this.unexecutedCommands.isEmpty()) {
            AbstractGameCommand command = this.unexecutedCommands.poll();
            command.doExecute();
            this.executedCommands.add(command);
        }
    }

    private void moveMissiles() {
        this.missiles.forEach(AbsMissile::move);
        this.destroyMissiles();
        this.notifyObservers();
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
        this.notifyObservers();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers();
    }

    public void cannonShoot() {
        this.missiles.addAll(this.cannon.shoot());
        this.notifyObservers();
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers();
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers();
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        this.notifyObservers();
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(IObserver::update);
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
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
        this.notifyObservers();
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

    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCommands.isEmpty()) {
            this.executedCommands.pop().unExecute();
            this.notifyObservers();
        }
    }
}
