package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.command.IncreaseCannonBatchCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class IncreaseBatchKeyHandler extends AbsHandler {
    
    public IncreaseBatchKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.INCREASE_BATCH_KEY)){
            this.model.registerCommand(new IncreaseCannonBatchCommand(model));
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
