package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public class GameGraphics implements IGameGraphics {

    private final IGameGraphicsImplementor implementor;

    public GameGraphics(IGameGraphicsImplementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Position position) {
        this.implementor.drawImage(path, position);
    }

    @Override
    public void drawText(String text, Position position) {
        this.implementor.drawText(text, position);
    }

    @Override
    public void drawRectangle(Position leftTop, Position rightBottom) {
        this.implementor.drawLine(leftTop, new Position(rightBottom.getX(), leftTop.getY()));
        this.implementor.drawLine(new Position(rightBottom.getX(), leftTop.getY()), rightBottom);
        this.implementor.drawLine(rightBottom, new Position(leftTop.getX(), rightBottom.getY()));
        this.implementor.drawLine(new Position(leftTop.getX(), rightBottom.getY()), leftTop);
    }

    @Override
    public void clear() {
        this.implementor.clear();
    }

    @Override
    public void drawLine(Position start, Position end) {
       this.implementor.drawLine(start, end);
    }
}
