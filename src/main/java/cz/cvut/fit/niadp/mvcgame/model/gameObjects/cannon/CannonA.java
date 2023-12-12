package cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;

import java.util.ArrayList;
import java.util.List;

public class CannonA extends AbsCannon {

    private IGameObjectsFactory gameObjectsFactory;
    private List<AbsMissile> shootingBatch;

    public CannonA(Position initPosition, IGameObjectsFactory gameObjectsFactory) {
        this.position = initPosition;
        this.gameObjectsFactory = gameObjectsFactory;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
        this.batchSize = MvcGameConfig.INIT_BATCH_SIZE;

        this.shootingBatch = new ArrayList<>();
        this.shootingMode = SINGLE_SHOOTING_MODE;
    }

    public CannonA(Position position, int power, double angle, int batchSize, List<AbsMissile> shootingBatch, IShootingMode shootingMode, IGameObjectsFactory gameObjectsFactory){
        this.position = position;
        this.power = power;
        this.angle = angle;
        this.batchSize = batchSize;
        this.shootingBatch = shootingBatch;
        this.shootingMode = shootingMode;
        this.gameObjectsFactory = gameObjectsFactory;
    }

    @Override
    public void moveUp() {
        if(this.position.getY() - MvcGameConfig.MOVE_STEP >= MvcGameConfig.CANNON_MIN_Y) {
            this.move(new Vector(0, -MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public void moveDown() {
        if(this.position.getY() + MvcGameConfig.MOVE_STEP <= MvcGameConfig.CANNON_MAX_Y) {
            this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public void aimUp() {
        if(this.angle - MvcGameConfig.ANGLE_STEP > MvcGameConfig.MIN_ANGLE){
            this.angle -= MvcGameConfig.ANGLE_STEP;
        }
    }

    @Override
    public void aimDown() {
        if(this.angle + MvcGameConfig.ANGLE_STEP < MvcGameConfig.MAX_ANGLE){
           this.angle += MvcGameConfig.ANGLE_STEP;
        }
    }

    @Override
    public void powerUp() {
        this.power = Math.min(MvcGameConfig.MAX_POWER, this.power + MvcGameConfig.POWER_STEP);
    }

    @Override
    public void powerDown() {
        this.power = Math.max(MvcGameConfig.MIN_POWER, this.power - MvcGameConfig.POWER_STEP);
    }

    @Override
    public void increaseBatch() {
        if(this.batchSize < MvcGameConfig.MAX_BATCH_SIZE && this.shootingMode instanceof DynamicShootingMode){
            this.batchSize += 1;
        }
    }

    @Override
    public void decreaseBatch() {
        if(this.batchSize > MvcGameConfig.MIN_BATCH_SIZE && this.shootingMode instanceof DynamicShootingMode){
            this.batchSize -= 1;
        }
    }

    @Override
    public void primitiveShoot() {
        this.shootingBatch.add(this.gameObjectsFactory.createMissile(this.angle, this.power));
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootingBatch.clear();
        this.shootingMode.shoot(this);
        return this.shootingBatch;
    }

    @Override
    public void toggleShootingMode() {
        if(this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        }
        else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = DYNAMIC_SHOOTING_MODE;
        }
        else if (this.shootingMode instanceof DynamicShootingMode) {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
    }

    @Override
    public CannonA clone() {
        return new CannonA(
            new Position(this.position.getX(), this.position.getY()),
            this.power,
            this.angle,
            this.batchSize,
            this.shootingBatch.stream().map(m -> this.gameObjectsFactory.createMissile(m.getInitAngle(), m.getInitVelocity())).toList(),
            this.shootingMode, 
            this.gameObjectsFactory
        );
    }
}
