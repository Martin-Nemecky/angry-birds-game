package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.AimCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimDownKeyHandler extends AbsHandler {
    
    public AimDownKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.AIM_DOWN_KEY)){
            this.model.registerCommand(new AimCannonDownCommand(model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
