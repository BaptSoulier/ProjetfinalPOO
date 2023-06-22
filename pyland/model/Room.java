package model;

public class Room implements IRoom {

    IPlayer player = getVisitor();

    public Room() {
        this.player = null;
    }

    public IPlayer getVisitor() {
        return this.player;
    }

    public void setVisitor(IPlayer v) {
        this.player = v;
    }

    public void unsetVisitor() {
        this.player = null;
    }
}