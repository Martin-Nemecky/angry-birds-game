package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.collision_detection.ICollisionDetector;
import cz.cvut.fit.niadp.mvcgame.collision_detection.SimpleCollisionDetector;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.info.AbsGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.levels.manager.ILevelManager;
import cz.cvut.fit.niadp.mvcgame.model.levels.manager.LevelManager;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.specific.SimpleAspect;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SystematicMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.ReverseRealisticMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class GameModel implements IGameModel {

    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    private final AbsGameInfo gameInfo;

    private IGameObjectsFactory gameObjectsFactory;

    private final ILevelManager levelManager;

    private IMovingStrategy movingStrategy;

    private final Map<AspectType, Set<IObserver>> observers;
    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        this.observers = new HashMap<>();
        for(AspectType aspectType : AspectType.values()){
            this.observers.put(aspectType, new HashSet<>());
        }
        
        this.gameObjectsFactory = GameObjectsFactoryA.getInstance();
        this.gameObjectsFactory.setModel(this);

        this.levelManager = new LevelManager(this.gameObjectsFactory);
        
        this.cannon = this.gameObjectsFactory.createCannon();
        this.missiles = new ArrayList<>();
        this.gameInfo = this.gameObjectsFactory.createGameInfo(); 

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

        int prevSize = this.missiles.size();
        this.destroyMissiles();
        int currentSize = this.missiles.size();

        if (this.missiles.isEmpty() && prevSize != currentSize) {
            this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
        } else {
            this.missiles.forEach(missile -> this.notifyObservers(new SimpleAspect(AspectType.MISSILE_MOVED, missile)));
        }
    }

    private void destroyMissiles() {
        this.missiles.removeAll(
            this.missiles.stream().filter(missile -> {
                return isOutOfScreen(missile) || hasCollided(missile);
            } ).toList()
        );
    }

    private boolean hasCollided(AbsMissile missile) {
        ICollisionDetector detector = SimpleCollisionDetector.getInstance();
        List<AbsBound> bounds = this.levelManager.getLevelBounds();
        List<AbsEnemy> enemies = this.levelManager.getLevelEnemies();

        for(AbsBound bound : bounds) {
            if(detector.detectCollision(missile, bound)){
                this.notifyObservers(new SimpleAspect(AspectType.MISSILE_COLLISION, missile));
                return true;
            }
        }

        for(AbsEnemy enemy : enemies) {
            if(detector.detectCollision(missile, enemy)){
                if(! enemy.isHurt()){
                    enemy.setIsHurt(true);
                    this.gameInfo.increaseScore(5);
                    this.notifyObservers(new SimpleAspect(AspectType.ENEMY_ATTACKED, enemy));
                } else {
                    this.levelManager.removeEnemy(enemy);
                    this.gameInfo.increaseScore(10);
                    this.notifyObservers(new SimpleAspect(AspectType.ENEMY_DESTROYED, enemy));

                    if(this.levelManager.getLevelEnemies().size() == 0){
                        this.levelManager.nextLevel();
                        this.gameInfo.increaseScore(50);
                        this.gameInfo.increaseLevel();
                        this.notifyObservers(new SimpleAspect(AspectType.NEXT_LEVEL, enemy));
                    }
                }

                return true;
            }
        }

        return false;
    }

    private boolean isOutOfScreen(AbsMissile missile) {
        return (
            missile.getPosition().getX() >= MvcGameConfig.MAX_X ||
            missile.getPosition().getX() <= 0 ||
            missile.getPosition().getY() >= MvcGameConfig.MAX_Y ||
            missile.getPosition().getY() <= 0
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

    public void increaseCannonBatch() {
        this.cannon.increaseBatch();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    public void decreaseCannonBatch() {
        this.cannon.decreaseBatch();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
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
        this.observers.get(aspect.getAspectType()).forEach(observer -> observer.update(aspect.getAspectType()));
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
    }

    public AbsCannon getCannon() {
        return this.cannon;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> temp1 = Stream.concat(Stream.of(this.cannon), this.missiles.stream()).toList();
        List<GameObject> temp2 = Stream.concat(Stream.of(this.gameInfo), temp1.stream()).toList();
        return Stream.concat(temp2.stream(), this.levelManager.getLevelGameObjects().stream()).toList();
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (this.movingStrategy instanceof SimpleMovingStrategy) {
            this.movingStrategy = new RealisticMovingStrategy();
        }
        else if (this.movingStrategy instanceof RealisticMovingStrategy) {
            this.movingStrategy = new ReverseRealisticMovingStrategy();
        }
        else if(this.movingStrategy instanceof ReverseRealisticMovingStrategy) {
            this.movingStrategy = new SystematicMovingStrategy();
        } else if(this.movingStrategy instanceof SystematicMovingStrategy){
            this.movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
    }

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;
        private AbsGameInfo gameInfo;
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
            this.notifyObservers(new SimpleAspect(AspectType.CANNON_MOVED, this.cannon));
        }
    }
}
