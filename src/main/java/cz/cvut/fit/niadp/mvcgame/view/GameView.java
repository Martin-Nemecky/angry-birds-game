package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.view.NullableObject.IGraphicsContext;
import cz.cvut.fit.niadp.mvcgame.view.NullableObject.NullGraphicsContext;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsRender;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private IGraphicsContext gr = NullGraphicsContext.getInstance();
    private final GameObjectsRender render;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.model.registerObserver(this, new AspectType[]{AspectType.POSITION_CHANGE, AspectType.CANNON_SHOOT});
        this.render = new GameObjectsRender();
    }

    public GameController getController() {
        return this.controller;
    }

    private <T> void render(T data) {
        // Clear the canvas
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        this.model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(this.render));
        //this.drawCannon(data);
    }

    public void setGraphicsContext(IGraphicsContext gr) {
        if(gr == null) {
            this.gr = NullGraphicsContext.getInstance();
            this.render.setGraphicsContext(this.gr);
        } else {
            this.gr = gr;
            this.render.setGraphicsContext(this.gr);
        }
        
        this.render(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y));
    }

    @Override
    public <T> void update(T data) {
        this.render(data);
    }
}
