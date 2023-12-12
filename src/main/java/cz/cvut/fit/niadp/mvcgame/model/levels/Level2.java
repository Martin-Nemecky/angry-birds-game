package cz.cvut.fit.niadp.mvcgame.model.levels;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;

public class Level2 extends AbsLevel {

    public Level2(IGameObjectsFactory factory) {
        super(factory);
        init();
    }

    public Level2(List<AbsBound> bounds, List<AbsEnemy> enemies, IGameObjectsFactory factory){
        super(bounds, enemies, factory);
    }

    private void init() {
        this.bounds.addAll(List.of(
            gameObjectsFactory.createBound(new Position(1170, 660)),
            gameObjectsFactory.createBound(new Position(1200, 660)),
            gameObjectsFactory.createBound(new Position(1230, 660)),

            gameObjectsFactory.createBound(new Position(870, 330)),
            gameObjectsFactory.createBound(new Position(900, 330)),
            gameObjectsFactory.createBound(new Position(930, 330)),
            gameObjectsFactory.createBound(new Position(930, 330)),
            gameObjectsFactory.createBound(new Position(930, 300)),
            gameObjectsFactory.createBound(new Position(930, 270)),

            gameObjectsFactory.createBound(new Position(570, 530)),
            gameObjectsFactory.createBound(new Position(600, 530)),
            gameObjectsFactory.createBound(new Position(630, 530)),

            gameObjectsFactory.createBound(new Position(770, 730)),
            gameObjectsFactory.createBound(new Position(800, 730)),
            gameObjectsFactory.createBound(new Position(830, 730)),

            gameObjectsFactory.createBound(new Position(1570, 430)),
            gameObjectsFactory.createBound(new Position(1600, 430)),
            gameObjectsFactory.createBound(new Position(1630, 430))
        ));


        this.enemies.addAll(List.of(
            gameObjectsFactory.createEnemy(new Position(1200, 630), false),
            gameObjectsFactory.createEnemy(new Position(900, 300), false),
            gameObjectsFactory.createEnemy(new Position(600, 500), false),
            gameObjectsFactory.createEnemy(new Position(800, 700), false),
            gameObjectsFactory.createEnemy(new Position(1600, 400), false)
        ));
    }

    @Override
    public Level2 clone() {
        return new Level2(getBounds(), getEnemies(), this.gameObjectsFactory);
    }
}
