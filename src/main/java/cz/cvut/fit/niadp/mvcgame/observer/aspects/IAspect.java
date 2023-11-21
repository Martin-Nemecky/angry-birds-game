package cz.cvut.fit.niadp.mvcgame.observer.aspects;

public interface IAspect<T> {
    
    AspectType getAspectType();
    
    T getData();
}
