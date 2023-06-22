package model;

public class Player implements IPlayer {
    IPlayer p;
    IRoom room = getLocation();

    public Player() {
        this.room = null;
        hasLeft();
    }

    public IRoom getLocation() {
        return this.room;
    }

    public boolean hasLeft() {
        if (p != null) {
            return true;
        }
        return false;
    }

    public void leave() {
        if (!hasLeft()) {
            hasLeft();
            getLocation();
        }
    }

    public void setLocation(IRoom r) {
        if (!hasLeft() && getLocation() == null && r != null && r.getVisitor() == null || r.getVisitor() == this) {
            r.setVisitor(this);
            this.room = r;
        }
    }

    public void unsetLocation() {
        if (!hasLeft() && getLocation() != null) {
            this.room.unsetVisitor();
            this.room = null;
        }
    }
}