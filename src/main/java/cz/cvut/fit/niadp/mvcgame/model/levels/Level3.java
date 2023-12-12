package cz.cvut.fit.niadp.mvcgame.model.levels;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;

public class Level3 extends AbsLevel {

    public Level3(IGameObjectsFactory factory) {
        super(factory);

        init();
    }

    public Level3(List<AbsBound> bounds, List<AbsEnemy> enemies, IGameObjectsFactory factory){
        super(bounds, enemies, factory);
    }

    private void init() {
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

    @Override
    public Level3 clone() {
        return new Level3(getBounds(), getEnemies(), this.gameObjectsFactory);
    }
}
