package cz.cvut.fit.niadp.mvcgame.view.NullableObject;

import javafx.scene.image.Image;

public class NullGraphicsContext implements IGraphicsContext {

    private static NullGraphicsContext context;

    private NullGraphicsContext(){}

    public static NullGraphicsContext getInstance() {
        if(context == null){
            context = new NullGraphicsContext();
        }

        return context;
    }

    @Override
    public void clearRect(double x, double y, double w, double h) {}

    @Override
    public void drawImage(Image img, double x, double y) {}
    
}
