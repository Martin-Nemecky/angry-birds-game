package cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class EnemyA extends AbsEnemy {

    public EnemyA(Position position) {
        super(position);
    }

    public EnemyA(Position position, boolean isHurt) {
        super(position, isHurt);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
       visitor.visitEnemy(this);
    }

    @Override
    public EnemyA clone() {
        return new EnemyA(new Position(this.position.getX(), this.position.getY()), this.isHurt);
    }
    
}
