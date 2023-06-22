package pyland.model;

import pyland.util.Direction;

/**
 * Modélise le réseau de connexion entre les salles du labyrinthe.
 *
 * @inv <pre>
 *     forall r, s in IRoom, d in Direction :
 *         canExit(r, d)
 *             <==> getRoom(r, d) != null
 *                  && (getDoor(r, d) == null || !getDoor(r, d).isLocked())
 *         getRoom(r, d) == s <==> getRoom(s, d.opposite()) == r
 *         getRoom(r, d) == s ==> getDoor(r, d) == getDoor(s, d.opposite())
 *         getRoom(r, d) == null ==> getDoor(r, d) == null </pre>
 *
 * @cons <pre>
 *     $POST$
 *         forall r in IRoom, d in Direction : getRoom(r, d) == null </pre>
 */
public interface IRoomNetwork {

    // REQUETES

    /**
     * Indique s'il y a un passage dans la direction <code>d</code> à partir de
     *  <code>r</code>.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    boolean canExit(IRoom r, Direction d);

    /**
     * La porte qui sépare r de sa voisine dans la direction d.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    IDoor getDoor(IRoom r, Direction d);

    /**
     * La salle à laquelle on accède à partir de r, dans la direction d.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    IRoom getRoom(IRoom r, Direction d);

    // COMMANDES

    /**
     * Supprime tout passage de ce réseau.
     * @post <pre>
     *     forall r in IRoom, d in Direction : getRoom(r, d) == null </pre>
     */
    void clear();

    /**
     * Etablit un passage entre r1 et r2, dans la direction d.
     * @pre <pre>
     *     r1 != null && d != null && r2 != null </pre>
     * @post <pre>
     *     Let x ::= old getRoom(r1, d)
     *         y ::= old getRoom(r2, d.opposite())
     *     x != null && x != r2 ==> getRoom(x, d.opposite()) == null
     *     y != null && y != r1 ==> getRoom(y, d) == null
     *     getRoom(r1, d) == r2
     *     getDoor(r1, d) == null </pre>
     */
    void connect(IRoom r1, Direction d, IRoom r2);

    /**
     * Installe la porte door entre r et getRoom(r, d).
     * @pre <pre>
     *     r != null && d != null && door != null
     *     getRoom(r, d) != null </pre>
     * @post <pre>
     *     getDoor(r, d) == door </pre>
     */
    void install(IRoom r, Direction d, IDoor door);
}
