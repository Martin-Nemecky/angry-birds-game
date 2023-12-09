package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.EnemyA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public class GameObjectsRender implements IGameObjectsVisitor {
    private IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gameGraphics.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.getPosition());
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        this.gameGraphics.drawImage((MvcGameConfig.MISSILE_IMAGE_RESOURCE), missile.getPosition());
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        if(enemy instanceof EnemyA) {
            if(enemy.isHurt()){
                this.gameGraphics.drawImage(MvcGameConfig.HURT_ENEMY1_IMAGE_RESOURCE, enemy.getPosition());
            } else {
                this.gameGraphics.drawImage(MvcGameConfig.ENEMY1_IMAGE_RESOURCE, enemy.getPosition());
            }
        } else {
            this.gameGraphics.drawImage(MvcGameConfig.ENEMY2_IMAGE_RESOURCE, enemy.getPosition());
        }
    }

    @Override
    public void visitBound(AbsBound bound) {
        this.gameGraphics.drawImage((MvcGameConfig.BOUND_IMAGE_RESOURCE), bound.getPosition());
    }
}
