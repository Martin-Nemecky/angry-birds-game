package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.info.AbsGameInfo;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public class GameObjectsRender implements IGameObjectsVisitor {
    private IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gameGraphics.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.getPosition());
        
        int startX = cannon.getPosition().getX() + 40;
        int startY = cannon.getPosition().getY() + 20;
        
        int endX = (int) Math.round(Math.cos(cannon.getAngle()) * 100 * (cannon.getPower() / (double) MvcGameConfig.INIT_POWER)) + cannon.getPosition().getX() + 40; 
        int endY = (int) Math.round(Math.sin(cannon.getAngle()) * 100 * (cannon.getPower() / (double) MvcGameConfig.INIT_POWER)) + cannon.getPosition().getY() + 20;


        this.gameGraphics.drawLine(new Position(startX, startY), new Position(endX, endY));
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        this.gameGraphics.drawImage((MvcGameConfig.MISSILE_IMAGE_RESOURCE), missile.getPosition());
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        if(!enemy.isHurt()) {
            this.gameGraphics.drawImage(MvcGameConfig.ENEMY1_IMAGE_RESOURCE, enemy.getPosition());
        } else {
            this.gameGraphics.drawImage((MvcGameConfig.HURT_ENEMY1_IMAGE_RESOURCE), enemy.getPosition());
        }
    }

    @Override
    public void visitBound(AbsBound bound) {
        this.gameGraphics.drawImage((MvcGameConfig.BOUND_IMAGE_RESOURCE), bound.getPosition());
    }

    @Override
    public void visitGameInfo(AbsGameInfo info) {
        this.gameGraphics.drawRectangle(new Position(10, 40), new Position(110, 110));
        this.gameGraphics.drawText("Score: " + info.getScore(), new Position(20, 60));
        this.gameGraphics.drawText("Level: " + info.getLevel(), new Position(20, 90));
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        this.gameGraphics.drawImage((MvcGameConfig.COLLISION_IMAGE_RESOURCE), collision.getPosition());
    }
}
