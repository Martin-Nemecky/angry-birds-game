package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsRender;
import javafx.scene.canvas.GraphicsContext;

public class GameView implements IObserver {

    private final IGameModel model;
    private final GameController controller;
    private GraphicsContext gr;
    private final GameObjectsRender render;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.model.registerObserver(this);
        this.render = new GameObjectsRender();
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        // Clear the canvas
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
        this.model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(this.render));
    }

    public void setGraphicsContext(GraphicsContext gr) {
        this.gr = gr;
        this.render.setGraphicsContext(gr);
        this.render();
    }

    @Override
    public void update() {
        this.render();
    }
}
