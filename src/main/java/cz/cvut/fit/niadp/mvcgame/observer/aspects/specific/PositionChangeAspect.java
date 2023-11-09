package cz.cvut.fit.niadp.mvcgame.observer.aspects.specific;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.AspectType;
import cz.cvut.fit.niadp.mvcgame.observer.aspects.IAspect;

public class PositionChangeAspect implements IAspect<Position> {

    private final AspectType type;

    private final Position position;

    public PositionChangeAspect(Position position){
        this.position = position;
        this.type = AspectType.POSITION_CHANGE;
    }

    @Override
    public Position getData() {
        return this.position;    
    }

    @Override
    public AspectType getAspectType() {
        return this.type;
    }
    
}
