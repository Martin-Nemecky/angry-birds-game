package cz.cvut.fit.niadp.mvcgame.observer.aspects.specific;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;

public class SimpleAspect implements IAspect<GameObject> {

    private final AspectType type;

    private GameObject gameObject;

    public SimpleAspect(AspectType type) {
        this.type = type;
    }

    public SimpleAspect(AspectType aspectType, GameObject gameObject){
        this.gameObject = gameObject;
        this.type = aspectType;
    }

    @Override
    public GameObject getData() {
        return this.gameObject;    
    }

    @Override
    public AspectType getAspectType() {
        return this.type;
    }
    
}
