package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObjectsRender implements IGameObjectsVisitor {
    private GraphicsContext gr;

    public void setGraphicsContext(GraphicsContext gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gr.drawImage(
                new Image(MvcGameConfig.CANNON_IMAGE_RESOURCE),
                cannon.getPosition().getX(),
                cannon.getPosition().getY()
        );
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        this.gr.drawImage(
                new Image(MvcGameConfig.MISSILE_IMAGE_RESOURCE),
                missile.getPosition().getX(),
                missile.getPosition().getY()
        );
    }
}
