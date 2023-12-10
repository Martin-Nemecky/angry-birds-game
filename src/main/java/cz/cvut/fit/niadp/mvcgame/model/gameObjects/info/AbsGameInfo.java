package cz.cvut.fit.niadp.mvcgame.model.gameObjects.info;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.LifetimeUnlimitedGameObject;

public abstract class AbsGameInfo extends LifetimeUnlimitedGameObject {

    protected int score;

    protected int level;

    public AbsGameInfo(int score, int level) {
        this.score = score;
        this.level = level;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void increaseLevel() {
        this.level = (this.level % MvcGameConfig.LEVEL_COUNT) + 1;
    }

    @Override 
    public abstract AbsGameInfo clone();
    
}
