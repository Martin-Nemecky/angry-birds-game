package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.AimDownKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.AimUpKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.BombKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.DecreaseBatchKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.DownKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.ExitKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.IHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.IncreaseBatchKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.MovingStrategyKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.PowerDownKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.PowerUpKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.ShootKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.ShootingModeKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.UndoLastCommandKeyHandler;
import cz.cvut.fit.niadp.mvcgame.chain_of_responsibility.UpKeyHandler;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.List;

public class GameController {

    private final IGameModel model;

    private IHandler initHandler;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void init() {
        initHandler = new UpKeyHandler(model);

        IHandler handler2 = new DownKeyHandler(model);
        IHandler handler3 = new ShootKeyHandler(model);
        IHandler handler4 = new AimUpKeyHandler(model);
        IHandler handler5 = new AimDownKeyHandler(model);
        IHandler handler6 = new PowerUpKeyHandler(model);
        IHandler handler7 = new PowerDownKeyHandler(model);
        IHandler handler8 = new MovingStrategyKeyHandler(model);
        IHandler handler9 = new ShootingModeKeyHandler(model);
        IHandler handler10 = new IncreaseBatchKeyHandler(model);
        IHandler handler11 = new DecreaseBatchKeyHandler(model);
        IHandler handler12 = new BombKeyHandler(model);
        IHandler handler13 = new UndoLastCommandKeyHandler(model);
        IHandler handler14 = new ExitKeyHandler(model);

        initHandler.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler3.setNextHandler(handler4);
        handler4.setNextHandler(handler5);
        handler5.setNextHandler(handler6);
        handler6.setNextHandler(handler7);
        handler7.setNextHandler(handler8);
        handler8.setNextHandler(handler9);
        handler9.setNextHandler(handler10);
        handler10.setNextHandler(handler11);
        handler11.setNextHandler(handler12);
        handler12.setNextHandler(handler13);
        handler13.setNextHandler(handler14);
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            initHandler.handle(code);
        }
        this.model.update();
        pressedKeysCodes.clear();
    }
}
