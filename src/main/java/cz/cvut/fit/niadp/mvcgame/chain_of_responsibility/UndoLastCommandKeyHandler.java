package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class UndoLastCommandKeyHandler extends AbsHandler {
    
    public UndoLastCommandKeyHandler(IGameModel model) {
        super(model);
    }

    @Override
    public void handle(String request) {
        if(request.equals(MvcGameConfig.UNDO_LAST_COMMAND_KEY)){
            this.model.undoLastCommand();
            return;
        }

        if(this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    } 
}
