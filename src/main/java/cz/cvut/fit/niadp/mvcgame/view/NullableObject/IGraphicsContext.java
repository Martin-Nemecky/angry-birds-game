package cz.cvut.fit.niadp.mvcgame.view.NullableObject;

import javafx.scene.image.Image;

public interface IGraphicsContext {
    
    void clearRect(double x, double y, double w, double h);

    void drawImage(Image img, double x, double y);


}
