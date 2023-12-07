package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
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

    @Override
    public void moveUp() {
        this.move(new Vector(0, -MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
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
}
