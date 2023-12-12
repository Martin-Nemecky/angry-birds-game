package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.ToggleMovingStrategyCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class MovingStrategyKeyHandler extends AbsHandler {
    
    public MovingStrategyKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.MOVING_STRATEGY_KEY)){
            this.model.registerCommand(new ToggleMovingStrategyCommand(model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
