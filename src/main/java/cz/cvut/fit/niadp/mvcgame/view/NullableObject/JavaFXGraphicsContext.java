package cz.cvut.fit.niadp.mvcgame.view.NullableObject;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFXGraphicsContext implements IGraphicsContext {

    private final GraphicsContext gr;

    public JavaFXGraphicsContext(GraphicsContext gr) {
        Objects.requireNonNull(gr);
        this.gr = gr;
    }

    @Override
    public void clearRect(double x, double y, double w, double h) {
        gr.clearRect(x, y, w, h);
    }

    @Override
    public void drawImage(Image img, double x, double y) {
        gr.drawImage(img, x, y);
    }
    
}
