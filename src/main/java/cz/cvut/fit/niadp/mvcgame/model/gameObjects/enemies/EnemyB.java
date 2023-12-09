package cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class EnemyB extends AbsEnemy {

    public EnemyB(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
