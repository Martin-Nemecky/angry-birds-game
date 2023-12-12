package cz.cvut.fit.niadp.mvcgame.chain_of_responsibility;

public interface IHandler {
    
    void handle(String request);

    void setNextHandler(IHandler handler);
}
