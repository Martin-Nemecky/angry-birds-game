package cz.cvut.fit.niadp.mvcgame.model.gameObjects.collisions;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class CollisionA extends AbsCollision {

    public CollisionA(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCollision(this);
    }

    @Override
    public CollisionA clone() {
        return new CollisionA(new Position(this.position.getX(), this.position.getY()));
    }
    
}
