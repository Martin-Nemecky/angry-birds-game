package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ExitKeyHandler extends AbsHandler {
    
    public ExitKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.EXIT_KEY)){
            System.exit(0);
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
