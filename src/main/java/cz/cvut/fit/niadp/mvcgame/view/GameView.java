package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
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
        this.model.registerObserver(this, new AspectType[]{AspectType.CANNON_MOVED, AspectType.MISSILE_MOVED});
        this.render = new GameObjectsRender();
    }

    public GameController getController() {
        return this.controller;
    }

    private <T extends GameObject> void render(T data) {
        // Clear the canvas
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        this.model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(this.render));
    }

    public void setGraphicsContext(IGraphicsContext gr) {
        if(gr == null) this.gr = NullGraphicsContext.getInstance();
        else this.gr = gr;
        
        this.render.setGraphicsContext(this.gr);
        this.render(this.model.getCannon());
    }

    @Override
    public <T extends GameObject> void update(T data) {
        this.render(data);
    }
}
