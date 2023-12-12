package cz.cvut.fit.niadp.mvcgame.collision_detection;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.BoundA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.MissileA;
import cz.cvut.fit.niadp.mvcgame.strategy.FallingMovingStrategy;

public class SimpleCollisionDetectorTest {
    
    @Test
    public void detectCollisionTest() {
        ICollisionDetector detector = SimpleCollisionDetector.getInstance();

        Position missilePos = new Position(200, 100);
        Position boundPos = new Position(200, 100);

        AbsMissile missile = new MissileA(missilePos,0, 10, new FallingMovingStrategy());
        AbsBound bound = new BoundA(boundPos);
        
        boolean result = detector.detectCollision(missile, bound);
        boolean expectedResult = true;
        
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void detectCollisionTest2() {
        ICollisionDetector detector = SimpleCollisionDetector.getInstance();

        Position missilePos = new Position(200 + MvcGameConfig.GAME_OBJECT_SIZE, 100);
        Position boundPos = new Position(200, 100);

        AbsMissile missile = new MissileA(missilePos,0, 10, new FallingMovingStrategy());
        AbsBound bound = new BoundA(boundPos);
        
        boolean result = detector.detectCollision(missile, bound);
        boolean expectedResult = true;
        
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void detectCollisionTest3() {
        ICollisionDetector detector = SimpleCollisionDetector.getInstance();

        Position missilePos = new Position(200, 100 - MvcGameConfig.GAME_OBJECT_SIZE);
        Position boundPos = new Position(200, 100);

        AbsMissile missile = new MissileA(missilePos,0, 10, new FallingMovingStrategy());
        AbsBound bound = new BoundA(boundPos);
        
        boolean result = detector.detectCollision(missile, bound);
        boolean expectedResult = true;
        
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void noCollisionTest() {
        ICollisionDetector detector = SimpleCollisionDetector.getInstance();

        Position missilePos = new Position(200 + MvcGameConfig.GAME_OBJECT_SIZE + 1, 100);
        Position boundPos = new Position(200, 100);

        AbsMissile missile = new MissileA(missilePos,0, 10, new FallingMovingStrategy());
        AbsBound bound = new BoundA(boundPos);
        
        boolean result = detector.detectCollision(missile, bound);
        boolean expectedResult = false;
        
        Assert.assertEquals(result, expectedResult);
    }
}
