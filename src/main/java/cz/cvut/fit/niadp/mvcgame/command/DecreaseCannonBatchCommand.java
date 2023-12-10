package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class DecreaseCannonBatchCommand extends AbstractGameCommand {

    public DecreaseCannonBatchCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.decreaseCannonBatch();
    }
    
}