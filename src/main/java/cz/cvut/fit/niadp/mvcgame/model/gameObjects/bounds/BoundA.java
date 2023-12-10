package cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class BoundA extends AbsBound {

    public BoundA(Position position) {
        this.position = position;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitBound(this);
    }

    @Override
    public BoundA clone() {
        return new BoundA(new Position(this.position.getX(), this.position.getY()));
    }   
}
