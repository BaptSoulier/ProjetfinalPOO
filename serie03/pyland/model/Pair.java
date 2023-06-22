package pyland.model;

/**
 * @inv
 *     room() != null
 */
class Pair {

    // ATTRIBUTS

    private IRoom room;
    private IDoor door;

    // CONSTRUCTEURS

    /**
     * @pre
     *     room != null
     * @post
     *     room() == room
     *     door() == null
     */
    Pair(IRoom room) {
        assert room != null;

        this.room = room;
        this.door = null;
    }

    // REQUETES

    IRoom room() {
        return room;
    }

    IDoor door() {
        return door;
    }

    // COMMANDES

    /**
     * Associe une porte à cette paire.
     * @pre
     *     d != null
     * @post
     *     door() == d
     */
    void setDoor(IDoor d) {
        assert d != null;

        door = d;
    }
}
