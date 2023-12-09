package cz.cvut.fit.niadp.mvcgame.sound;

import java.io.File;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import javafx.scene.media.AudioClip;

public class SoundObserver implements IObserver {

    private IGameModel model;

    private AudioClip moveClip;
    private AudioClip shootClip;
    private AudioClip attactClip;
    private AudioClip destroyedClip;
    private AudioClip collisionClip;
    private AudioClip nextLevelClip;

    public SoundObserver(IGameModel model) {
        this.model = model;
        this.model.registerObserver(this, new AspectType[]{
            AspectType.CANNON_MOVED,
            AspectType.MISSILE_FIRED,
            AspectType.MISSILE_COLLISION,
            AspectType.ENEMY_ATTACKED,
            AspectType.ENEMY_DESTROYED,
            AspectType.NEXT_LEVEL
        });
    }

    public void init() {
        File moveFile = new File(getClass().getResource(MvcGameConfig.CANNON_MOVED_SOUND_RESOURCE).getFile());
        File fireFile = new File(getClass().getResource(MvcGameConfig.MISSILE_FIRED_SOUND_RESOURCE).getFile());
        File collisionFile = new File(getClass().getResource(MvcGameConfig.MISSILE_COLLISION_SOUND_RESOURCE).getFile());
        File attackFile = new File(getClass().getResource(MvcGameConfig.ENEMY_ATTACKED_SOUND_RESOURCE).getFile());
        File destroyedFile = new File(getClass().getResource(MvcGameConfig.ENEMY_DESTROYED_SOUND_RESOURCE).getFile());
        File nextLevelFile = new File(getClass().getResource(MvcGameConfig.NEXT_LEVEL_SOUND_RESOURCE).getFile());
        
        if(moveFile.exists()){
            moveClip =  new AudioClip(moveFile.toURI().toString());
        }

        if(fireFile.exists()){
            shootClip = new AudioClip(fireFile.toURI().toString());
        }

        if(collisionFile.exists()){
            collisionClip = new AudioClip(collisionFile.toURI().toString());
        }

        if(attackFile.exists()){
            attactClip = new AudioClip(attackFile.toURI().toString());
        }

        if(destroyedFile.exists()){
            destroyedClip = new AudioClip(destroyedFile.toURI().toString());
        }

        if(nextLevelFile.exists()){
            nextLevelClip = new AudioClip(nextLevelFile.toURI().toString());
        }
    }

    @Override
    public <T extends GameObject> void update(T data, AspectType type) {
        switch (type) {
            case CANNON_MOVED:
                if(!moveClip.isPlaying()) moveClip.play();
                break;
            case MISSILE_FIRED:
                shootClip.play();
                break;
            case MISSILE_COLLISION:
                collisionClip.play();
                break;
            case ENEMY_ATTACKED:
                attactClip.play();
                break;
            case ENEMY_DESTROYED:
                destroyedClip.play();
                break;
            case NEXT_LEVEL:
                nextLevelClip.play();
                break;
            default:
                break;
        }
    }
}
