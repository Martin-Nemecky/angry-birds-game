package cz.cvut.fit.niadp.mvcgame.observer.aspects.specific;

import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;

public class CannonShootAspect implements IAspect<String> {

    private final AspectType type;


    public CannonShootAspect(){
        this.type = AspectType.CANNON_SHOOT;
    }

    @Override
    public String getData() {
        return "Cannon fired...";    
    }

    @Override
    public AspectType getAspectType() {
        return this.type;
    }
    
}
