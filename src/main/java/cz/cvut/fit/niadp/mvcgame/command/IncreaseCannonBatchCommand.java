package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class IncreaseCannonBatchCommand extends AbstractGameCommand {

    public IncreaseCannonBatchCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.increaseCannonBatch();
    }
    
}
