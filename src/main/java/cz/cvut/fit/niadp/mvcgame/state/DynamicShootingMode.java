package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.cannon.AbsCannon;

public class DynamicShootingMode implements IShootingMode {

    @Override
    public String getName() {
        return DynamicShootingMode.class.getSimpleName();
    }

    @Override
    public void shoot(AbsCannon cannon) {
        int size = cannon.getBatchSize();
        for(int i = 0; i < Math.floor(size / 2.0); i++) {
            cannon.aimUp();
        }

        for(int i = 0; i < size; i ++) {
            cannon.primitiveShoot();
            cannon.aimDown();
        }

        for(int i = 0; i < Math.ceil(size / 2.0); i++) {
            cannon.aimUp();
        }
    }
    
}
