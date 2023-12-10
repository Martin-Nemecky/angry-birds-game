package cz.cvut.fit.niadp.mvcgame.model.levels;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.Position;

public class Level1 extends AbsLevel {

    public Level1(IGameObjectsFactory factory) {
        super(factory);

        this.bounds.addAll(List.of(
            gameObjectsFactory.createBound(new Position(970, 330)),
            gameObjectsFactory.createBound(new Position(1000, 330)),
            gameObjectsFactory.createBound(new Position(1030, 330)),

            gameObjectsFactory.createBound(new Position(1370, 530)),
            gameObjectsFactory.createBound(new Position(1400, 530)),
            gameObjectsFactory.createBound(new Position(1430, 530)),

            gameObjectsFactory.createBound(new Position(770, 730)),
            gameObjectsFactory.createBound(new Position(800, 730)),
            gameObjectsFactory.createBound(new Position(830, 730))
        ));


        this.enemies.addAll(List.of(
            gameObjectsFactory.createEnemy(new Position(1000, 300), false),
            gameObjectsFactory.createEnemy(new Position(1400, 500), false),
            gameObjectsFactory.createEnemy(new Position(800, 700), false)
        ));
    }
}