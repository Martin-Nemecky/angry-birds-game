package cz.cvut.fit.niadp.mvcgame.memento;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

import java.util.Stack;

public class CareTaker {
    private IGameModel model;

    private final Stack<Object> mementos = new Stack<>();

    private CareTaker() {

    }

    private static class SingletonHolder {
        private static final CareTaker INSTANCE = new CareTaker();
    }

    public static CareTaker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }

    public void createMemento() {
        if (this.model != null) {
            this.mementos.add(this.model.createMemento());
        }
    }

    public void setMemento() {
        if (this.model != null) {
            if (!this.mementos.isEmpty()) {
                this.model.setMemento(this.mementos.pop());
            }
        }
    }

}
