package cz.cvut.fit.niadp.mvcgame.visitor;

import java.io.File;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import javafx.scene.media.AudioClip;

public class GameObjectsSoundPlayer implements IGameObjectsVisitor {

    private AudioClip moveClip;

    private AudioClip shootClip;

    public GameObjectsSoundPlayer() {
        File moveFile = new File(getClass().getResource(MvcGameConfig.CANNON_MOVED_SOUND_RESOURCE).getFile());
        File fireFile = new File(getClass().getResource(MvcGameConfig.MISSILE_FIRED_SOUND_RESOURCE).getFile());
        
        if(moveFile.exists()){
            moveClip =  new AudioClip(moveFile.toURI().toString());
        }

        if(fireFile.exists()){
            shootClip = new AudioClip(fireFile.toURI().toString());
        }
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        if(!moveClip.isPlaying()){
            moveClip.play();
        }
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        shootClip.play();
    }
    
}
