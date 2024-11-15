package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ToggleShootingModeCommand extends AbstractGameCommand {

    public ToggleShootingModeCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.toggleShootingMode();
    }
    
}
