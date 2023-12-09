package cz.cvut.fit.niadp.mvcgame.collision_detection;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public class SimpleCollisionDetector implements ICollisionDetector {

    private static SimpleCollisionDetector instance;
    
    private SimpleCollisionDetector() {}
    
    public static SimpleCollisionDetector getInstance() {
        if(instance == null) {
            instance = new SimpleCollisionDetector();
        }

        return instance;
    }
    
    @Override
    public boolean detectCollision(GameObject a, GameObject b) {
        Position pA = a.getPosition();
        Position pB = b.getPosition();

        return (
            (
                pA.getX() <= pB.getX() + MvcGameConfig.GAME_OBJECT_SIZE &&
                pA.getX() + MvcGameConfig.GAME_OBJECT_SIZE >= pB.getX()
            ) && (
                pA.getY() <= pB.getY() + MvcGameConfig.GAME_OBJECT_SIZE &&
                pA.getY() + MvcGameConfig.GAME_OBJECT_SIZE >= pB.getY()
            )
        );
    }
    
}
