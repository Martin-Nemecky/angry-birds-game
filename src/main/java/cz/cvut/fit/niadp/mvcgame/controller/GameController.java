package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.command.AimCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.AimCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.DecreaseCannonBatchCommand;
import cz.cvut.fit.niadp.mvcgame.command.IncreaseCannonBatchCommand;
import cz.cvut.fit.niadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.PowerCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.PowerCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.ShootCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleMovingStrategyCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleShootingModeCommand;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

import java.util.List;

public class GameController {

    private final IGameModel model;
    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameConfig.UP_KEY:
                    this.model.registerCommand(new MoveCannonUpCommand(this.model));
                    break;
                case MvcGameConfig.DOWN_KEY:
                    this.model.registerCommand(new MoveCannonDownCommand(this.model));
                    break;
                case MvcGameConfig.SHOOT_KEY:
                    this.model.registerCommand(new ShootCommand(model));
                    break;
                case MvcGameConfig.AIM_UP_KEY:
                    this.model.registerCommand(new AimCannonUpCommand(model));
                    break;
                case MvcGameConfig.AIM_DOWN_KEY:
                    this.model.registerCommand(new AimCannonDownCommand(model));
                    break;
                case MvcGameConfig.POWER_UP_KEY:
                    this.model.registerCommand(new PowerCannonUpCommand(model));
                    break;
                case MvcGameConfig.POWER_DOWN_KEY:
                    this.model.registerCommand(new PowerCannonDownCommand(model));
                    break;
                case MvcGameConfig.MOVING_STRATEGY_KEY:
                    this.model.registerCommand(new ToggleMovingStrategyCommand(model));
                    break;
                case MvcGameConfig.SHOOTING_MODE_KEY:
                    this.model.registerCommand(new ToggleShootingModeCommand(model));
                    break;
                case MvcGameConfig.INCREASE_BATCH_KEY:
                    this.model.registerCommand(new IncreaseCannonBatchCommand(model));
                    break;
                case MvcGameConfig.DECREASE_BATCH_KEY:
                    this.model.registerCommand(new DecreaseCannonBatchCommand(model));
                    break;
                case MvcGameConfig.UNDO_LAST_COMMAND_KEY:
                    this.model.undoLastCommand();
                    break;
                case MvcGameConfig.EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
        this.model.update();
        pressedKeysCodes.clear();
    }
}
