package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;

public class SystematicMovingStrategy implements IMovingStrategy {
    
    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();

        int dX = 1;
        int dY = 1;
        
        int positionX = missile.getPosition().getX();
        if(positionX < MvcGameConfig.MAX_X / 4){
            dX = (int) (initVelocity * Math.cos(initAngle));
            dY = (int) (initVelocity * Math.sin(initAngle));
        } else if(positionX < MvcGameConfig.MAX_X / 2){
            dX = (int) (initVelocity * Math.cos(initAngle));
            dY = - (int) (initVelocity * Math.sin(initAngle));
        } else if (positionX < MvcGameConfig.MAX_X * 3 / 4) {
            dX = (int) (initVelocity * Math.cos(initAngle));
            dY = (int) (initVelocity * Math.sin(initAngle));
        } else {
            dX = (int) (initVelocity * Math.cos(initAngle));
            dY = - (int) (initVelocity * Math.sin(initAngle));
        }
        

        missile.move(new Vector(dX, dY));
    }
}
