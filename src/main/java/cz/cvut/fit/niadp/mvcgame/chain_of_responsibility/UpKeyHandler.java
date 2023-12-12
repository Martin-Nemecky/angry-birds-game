package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class UpKeyHandler extends AbsHandler {

    public UpKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.UP_KEY)){
            this.model.registerCommand(new MoveCannonUpCommand(this.model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    }
}
