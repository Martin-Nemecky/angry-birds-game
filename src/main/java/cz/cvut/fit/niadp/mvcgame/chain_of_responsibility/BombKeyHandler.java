package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.FireBombCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class BombKeyHandler extends AbsHandler {
    
    public BombKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.BOMB_KEY)){
            this.model.registerCommand(new FireBombCommand(model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
