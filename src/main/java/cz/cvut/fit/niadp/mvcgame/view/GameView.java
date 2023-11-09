package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.view.NullableObject.IGraphicsContext;
import cz.cvut.fit.niadp.mvcgame.view.NullableObject.NullGraphicsContext;
import javafx.scene.image.Image;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private IGraphicsContext gr = NullGraphicsContext.getInstance();

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.model.registerObserver(this, new AspectType[]{AspectType.POSITION_CHANGE});
    }

    public GameController getController() {
        return this.controller;
    }

    private <T> void render(T data) {
        // Clear the canvas
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        this.drawCannon(data);
    }

    private <T> void drawCannon(T positionData) {
        Position cannonPosition = null;

        if(! (positionData instanceof Position)){
            return;
        } else {
            cannonPosition = ((Position) positionData);
        }
        
        this.gr.drawImage(new Image(MvcGameConfig.CANNON_IMAGE_RESOURCE), cannonPosition.getX(), cannonPosition.getY());
    }

    public void setGraphicsContext(IGraphicsContext gr) {
        if(gr == null) {
            this.gr = NullGraphicsContext.getInstance();
        } else {
            this.gr = gr;
        }
        
        this.render(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y));
    }

    @Override
    public <T> void update(T data) {
        this.render(data);
    }
}
