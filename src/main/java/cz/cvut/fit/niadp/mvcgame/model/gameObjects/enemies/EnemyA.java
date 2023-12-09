package cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class EnemyA extends AbsEnemy {

    public EnemyA(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
       visitor.visitEnemy(this);
    }
    
}
