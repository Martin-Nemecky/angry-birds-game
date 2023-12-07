package cz.cvut.fit.niadp.mvcgame.sound;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsSoundPlayer;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

public class SoundObserver implements IObserver {

    private IGameModel model;

    private IGameObjectsVisitor soundVisitor;

    public SoundObserver(IGameModel model) {
        this.model = model;
        this.model.registerObserver(this, new AspectType[]{AspectType.CANNON_MOVED, AspectType.MISSILE_FIRED});
        this.soundVisitor = new GameObjectsSoundPlayer();
    }

    @Override
    public <T extends GameObject> void update(T data) {
        data.acceptVisitor(soundVisitor);
    }
    
}
