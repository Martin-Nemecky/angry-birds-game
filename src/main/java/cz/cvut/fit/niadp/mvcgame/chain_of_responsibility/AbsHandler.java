package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public abstract class AbsHandler implements IHandler {

    protected IHandler nextHandler;

    protected IGameModel model;

    protected AbsHandler(IGameModel model) {
        this.model = model;
    }
    
    @Override
    public void setNextHandler(IHandler handler) {
        this.nextHandler = handler;
    }
}
