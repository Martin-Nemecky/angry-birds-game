package cz.cvut.fit.niadp.mvcgame.collision_detection;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

public interface ICollisionDetector {
    
    boolean detectCollision(GameObject a, GameObject b);
}
