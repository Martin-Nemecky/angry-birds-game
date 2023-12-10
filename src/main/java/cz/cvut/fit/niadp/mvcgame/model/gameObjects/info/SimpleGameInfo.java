package cz.cvut.fit.niadp.mvcgame.model.gameObjects.info;

import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class SimpleGameInfo extends AbsGameInfo {

    public SimpleGameInfo() {
        super(0, 1);
    }

    public SimpleGameInfo(int score, int level) {
        super(score, level);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    @Override
    public SimpleGameInfo clone() {
        return new SimpleGameInfo(this.score, this.level);
    }

}
