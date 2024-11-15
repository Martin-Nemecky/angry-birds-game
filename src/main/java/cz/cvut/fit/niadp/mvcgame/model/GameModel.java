package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
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
import cz.cvut.fit.niadp.mvcgame.strategy.FallingMovingStrategy;
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
    private final List<AbsCollision> collisions;
    private final AbsGameInfo gameInfo;

    private ICollisionDetector collisionDetector;
    private IGameObjectsFactory gameObjectsFactory;

    private ILevelManager levelManager;

    private IMovingStrategy movingStrategy;

    private final Map<AspectType, Set<IObserver>> observers;
    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        this.observers = new HashMap<>();
        for(AspectType aspectType : AspectType.values()){
            this.observers.put(aspectType, new HashSet<>());
        }
        
        this.collisionDetector = SimpleCollisionDetector.getInstance();
        this.gameObjectsFactory = GameObjectsFactoryA.getInstance();
        this.gameObjectsFactory.setModel(this);

        this.levelManager = new LevelManager(this.gameObjectsFactory);
        
        this.missiles = new ArrayList<>();
        this.collisions = new ArrayList<>();
        this.cannon = this.gameObjectsFactory.createCannon();
        this.gameInfo = this.gameObjectsFactory.createGameInfo(); 

        this.movingStrategy = new SimpleMovingStrategy();
        this.unexecutedCommands = new LinkedBlockingQueue<>();
        this.executedCommands = new Stack<>();
    }

    public void update() {
        this.executeCommands();
        this.moveMissiles();
        this.destroyMissiles();
        this.removeOutdatedCollisions();
        this.tryLevellingUp();
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
        this.notifyObservers(new SimpleAspect(AspectType.MISSILE_FIRED));
    }

    public void fireBomb() {
        List<AbsMissile> missiles = new ArrayList<>();
        int spacing = (MvcGameConfig.MAX_X - 500) / MvcGameConfig.BOMB_BATCH_SIZE;
        for(int i = 0; i < MvcGameConfig.BOMB_BATCH_SIZE; i++){
            missiles.add(this.gameObjectsFactory.createMissile(
                new Position(250 + spacing * i, 30),
                MvcGameConfig.INIT_ANGLE,
                MvcGameConfig.INIT_POWER,
                new FallingMovingStrategy()
            ));
        }

        this.missiles.addAll(missiles);
        this.notifyObservers(new SimpleAspect(AspectType.DEFAULT));
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

    // ================================== Getters & Setters ==================================

    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    public AbsCannon getCannon() {
        return this.cannon;
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> temp1 = Stream.concat(Stream.of(this.cannon), this.missiles.stream()).toList();
        List<GameObject> temp2 = Stream.concat(Stream.of(this.gameInfo), temp1.stream()).toList();
        List<GameObject> temp3 = Stream.concat(this.collisions.stream(), temp2.stream()).toList();
        return Stream.concat(temp3.stream(), this.levelManager.getLevelGameObjects().stream()).toList();
    }

    // ================================== Observers ==================================

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

    // ================================== Commands ==================================

    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCommands.isEmpty()) {
            this.executedCommands.pop().unExecute();
            this.notifyObservers(new SimpleAspect(AspectType.DEFAULT));
        }
    }

    // ================================== Memento ==================================

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;
        private List<AbsEnemy> enemies;
        private int currentLevel;
        private int score;
    }

    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = this.getCannonPosition().getX();
        gameModelSnapshot.cannonPositionY = this.getCannonPosition().getY();
        gameModelSnapshot.enemies = this.levelManager.getLevelEnemies();
        gameModelSnapshot.currentLevel = this.levelManager.getCurrentLevelNumber();
        gameModelSnapshot.score = this.gameInfo.getScore();
        
        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        this.cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        this.cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        this.levelManager.setCurrentLevelNumber(gameModelSnapshot.currentLevel);
        this.levelManager.setLevelEnemies(gameModelSnapshot.enemies);
        this.gameInfo.setScore(gameModelSnapshot.score);
        this.gameInfo.setLevel(gameModelSnapshot.currentLevel);
    }

    // ================================== PRIVATE METHODS ==================================

    private void moveMissiles() {
        this.missiles.forEach(AbsMissile::move);
    }

    private void destroyMissiles() {
        int prevSize = this.missiles.size();
        this.missiles.removeAll(
            this.missiles.stream().filter(missile -> {
                return isOutOfScreen(missile) || hasCollided(missile);
            } ).toList()
        );
        int currentSize = this.missiles.size();

        if (this.missiles.isEmpty() && prevSize != currentSize) {
            this.notifyObservers(new SimpleAspect(AspectType.DEFAULT));
        } else {
            this.missiles.forEach(missile -> this.notifyObservers(new SimpleAspect(AspectType.MISSILE_MOVED, missile)));
        }
    }

    private void removeOutdatedCollisions() {
        this.collisions.removeAll(
            this.collisions.stream().filter(collision -> {
                if(collision.getAge() >= 500){
                    this.levelManager.addHurtEnemy(this.gameObjectsFactory.createEnemy(collision.getPosition(), true));
                    return true;
                } 
                return false;
            } ).toList()
        );
        this.notifyObservers(new SimpleAspect(AspectType.DEFAULT));
    }

    private void tryLevellingUp() {
        if(this.levelManager.getLevelEnemies().size() == 0 && this.collisions.size() == 0){
            this.levelManager.nextLevel();
            this.gameInfo.increaseScore(50);
            this.gameInfo.increaseLevel();
            this.notifyObservers(new SimpleAspect(AspectType.NEXT_LEVEL));
        }
    }

    private boolean hasCollided(AbsMissile missile) {
        List<AbsBound> bounds = this.levelManager.getLevelBounds();
        List<AbsEnemy> enemies = this.levelManager.getLevelEnemies();

        for(AbsBound bound : bounds) {
            if(collisionDetector.detectCollision(missile, bound)){
                this.notifyObservers(new SimpleAspect(AspectType.MISSILE_COLLISION, missile));
                return true;
            }
        }

        for(AbsEnemy enemy : enemies) {
            if(collisionDetector.detectCollision(missile, enemy)){
                if(! enemy.isHurt()){
                    this.levelManager.removeEnemy(enemy);
                    this.collisions.add(this.gameObjectsFactory.createCollision(enemy.getPosition()));
                    this.gameInfo.increaseScore(5);
                    this.notifyObservers(new SimpleAspect(AspectType.ENEMY_ATTACKED, enemy));
                } else {
                    this.levelManager.removeEnemy(enemy);
                    this.gameInfo.increaseScore(10);
                    this.notifyObservers(new SimpleAspect(AspectType.ENEMY_DESTROYED, enemy));
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

    private void executeCommands() {
        while(!this.unexecutedCommands.isEmpty()) {
            AbstractGameCommand command = this.unexecutedCommands.poll();
            command.doExecute();
            this.executedCommands.add(command);
        }
    }
}
