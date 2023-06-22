package pyland.model;

/**
 * @cons <pre>
 *     $POST$
 *         getVisitor() == null
 *         getKey() == null </pre>
 */
public interface IKeyRoom extends INormalRoom {

    // REQUETES

    /**
     * Retourne la clé ou null s'il n'y en a pas.
     */
    Key getKey();

    // COMMANDES

    /**
     * Dépose la clé key dans cette salle.
     * @pre <pre>
     *     key != null && getKey() == null </pre>
     * @post <pre>
     *     getVisitor() == old getVisitor()
     *     getKey() == key </pre>
     */
    void putKey(Key key);

    /**
     * Ramasse la clé de cette salle.
     * @post <pre>
     *     getVisitor() == old getVisitor()
     *     getKey() == null </pre>
     */
    void removeKey();

    /**
     * @post <pre>
     *     getKey() == old getKey() </pre>
     */
    void setVisitor(IPlayer v);

    /**
     * @post <pre>
     *     getKey() == old getKey() </pre>
     */
    void unsetVisitor();
}
