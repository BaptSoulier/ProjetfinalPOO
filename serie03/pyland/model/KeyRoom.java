package pyland.model;

public class KeyRoom extends NormalRoom implements IKeyRoom{

    private Key cle;

    /**
     * Retourne la clé ou null s'il n'y en a pas.
     */
    public Key getKey() {return this.cle;};

    // COMMANDES

    /**
     * Dépose la clé key dans cette salle.
     * @pre <pre>
     *     key != null && getKey() == null </pre>
     * @post <pre>
     *     getVisitor() == old getVisitor()
     *     getKey() == key </pre>
     */
    public void putKey(Key key) {
        this.cle = key;
    };

    /**
     * Ramasse la clé de cette salle.
     * @post <pre>
     *     getVisitor() == old getVisitor()
     *     getKey() == null </pre>
     */
    public void removeKey() {
        this.cle = null;
    };

    /**
     * @post <pre>
     *     getKey() == old getKey() </pre>
     */
    public void setVisitor(IPlayer v) {
        //v.pickUpKey(this.getKey());
        super.setVisitor(v);
    };

    /**
     * @post <pre>
     *     getKey() == old getKey() </pre>
     */
    public void unsetVisitor() {
        //this.removeKey();
        super.unsetVisitor();
    };
}
