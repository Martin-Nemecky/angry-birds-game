package cz.cvut.fit.niadp.mvcgame.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.abstract_factory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstract_factory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.AbsBound;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.bounds.BoundA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.enemies.EnemyA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.missiles.MissileA;
import cz.cvut.fit.niadp.mvcgame.model.levels.manager.LevelManager;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class GameModelCollisionTest {
    
    private static final String HAS_COLLIDED_METHOD_NAME = "hasCollided";
    private static final boolean EXPECTED_RESULT = true;

    @Mocked
    private LevelManager model;

    @Test
    public void hasCollided() throws NoSuchMethodException, SecurityException {
        this.generalMockSetup();
        GameModel model = new GameModel();
        Method method = model.getClass().getDeclaredMethod(HAS_COLLIDED_METHOD_NAME, AbsMissile.class);
        method.setAccessible(true);
        
        boolean result = false;
        try {
            result = (boolean) method.invoke(model, new MissileA(new Position(200, 100), 0, 10, new SimpleMovingStrategy()));
        } catch (IllegalAccessException | InvocationTargetException ignored) {}
       
        method.setAccessible(false);
        Assert.assertEquals(EXPECTED_RESULT, result);

    }


    public void generalMockSetup() {
        new MockUp<LevelManager>() {
            @Mock
            public List<AbsBound> getLevelBounds() {
                return List.of(new BoundA(new Position(200, 100)));
            }

            @Mock
            public List<AbsEnemy> getLevelEnemies() {
                return List.of(new EnemyA(new Position(200, 200), false));
            }
        };
    }
}
