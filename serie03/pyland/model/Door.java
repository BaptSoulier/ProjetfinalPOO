package pyland.model;

public class Door implements IDoor {

    private Key cle;

    private boolean locked;

    public Door(Key key) {
        this.cle = key;
        this.locked = true;
    }
    /**
     * Indique si <code>key</code> est bien adaptée à cette porte.
     * @pre <pre>
     *     key != null </pre>
     */
    public boolean keyholeMatchWith(Key key) {
        if (this.cle == key) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * Indique si la porte est fermée ou non.
     */
    public boolean isLocked() {
        return this.locked;
    };

    // COMMANDES

    /**
     * Ferme cette porte avec <code>key</code>.
     * @pre <pre>
     *     key != null </pre>
     * @post <pre>
     *     keyholeMatchWith(key) ==> isLocked()
     *     !keyholeMatchWith(key) ==> isLocked() == old isLocked() </pre>
     */
    public void lock(Key key) {
        if (this.cle != key) {
            this.locked = true;
        }
    };

    /**
     * Ouvre cette porte avec <code>key</code>.
     * @pre <pre>
     *     key != null </pre>
     * @post <pre>
     *     keyholeMatchWith(key) ==> !isLocked()
     *     !keyholeMatchWith(key) ==> isLocked() == old isLocked() </pre>
     */
    public void unlock(Key key) {
        if (this.cle == key) {
            this.locked = false;
        }
    };
}
