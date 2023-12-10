package cz.cvut.fit.niadp.mvcgame.model.levels;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.Position;

public class Level3 extends AbsLevel {

    public Level3(IGameObjectsFactory factory) {
        super(factory);

        this.bounds.addAll(List.of(
            gameObjectsFactory.createBound(new Position(1240, 600)),
            gameObjectsFactory.createBound(new Position(1240, 630)),
            gameObjectsFactory.createBound(new Position(1270, 630)),
            gameObjectsFactory.createBound(new Position(1300, 630)),
            gameObjectsFactory.createBound(new Position(1330, 630)), 
            gameObjectsFactory.createBound(new Position(1360, 630)),
            gameObjectsFactory.createBound(new Position(1360, 600))
        ));


        this.enemies.addAll(List.of(
            gameObjectsFactory.createEnemy(new Position(1300, 600), false)
        ));
    }
}
