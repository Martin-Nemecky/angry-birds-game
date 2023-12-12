package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class DownKeyHandler extends AbsHandler {
    
    public DownKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.DOWN_KEY)){
            this.model.registerCommand(new MoveCannonDownCommand(this.model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    }
}
