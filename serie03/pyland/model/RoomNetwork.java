package pyland.model;

import java.util.HashMap;
import java.util.Map;

import pyland.util.Direction;

class RoomNetwork implements IRoomNetwork {

    // ATTRIBUTS



    /**
     * Map dont les clés sont de type IRoom et les valeurs de type Map.
     * Pour chaque valeur de type Map, ses clés sont de type Direction et ses
     *  valeurs de type IRoom.
     */
    private final Map data;

    // CONSTRUCTEURS

    RoomNetwork() {
        data = new HashMap();
    }

    // REQUETES

    /**
     * Indique s'il y a un passage dans la direction <code>d</code> à partir de
     *  <code>r</code>.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    public boolean canExit(IRoom r, Direction d) {
        if (r == null || d == null) {
            throw new AssertionError();
        }
        Pair pair = getPairImpl(r, d);
        // On regarde si l'état de la paire
        if (pair == null) {
            return false;
        } else {
            // on regarde l'etat de la porte
            IDoor door = pair.door();
            if (door == null) {
                return true;
            } else {
                // on regarde si la porte est ouverte ...
                if (door.isLocked()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        //return getRoomImpl(r, d) != null;
    }

    /**
     * La porte qui sépare r de sa voisine dans la direction d.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    public IDoor getDoor(IRoom r, Direction d) {
        if (r == null || d == null) {
            throw new AssertionError();
        }
        Pair pair = getPairImpl(r, d);
        if (pair == null) {
            return null;
        } else {
            return pair.door();
        }
    };

    /**
     * La salle à laquelle on accède à partir de r, dans la direction d.
     * @pre <pre>
     *     r != null && d != null </pre>
     */
    public IRoom getRoom(IRoom r, Direction d) {
        if (r == null || d == null) {
            throw new AssertionError();
        }
        Pair pair = getPairImpl(r, d);
        if (pair == null) {
            return null;
        } else {
            return pair.room();
        }
    }

    // COMMANDES
    /**
     * Supprime tout passage de ce réseau.
     * @post <pre>
     *     forall r in IRoom, d in Direction : getRoom(r, d) == null </pre>
     */
    public void clear() {
        data.clear();
    }

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
    public void connect(IRoom r1, Direction d, IRoom r2) {
        if (r1 == null || d == null || r2 == null) {
            throw new AssertionError();
        }

        IRoom x = getRoom(r1, d);
        IRoom y = getRoom(r1, d.opposite());
        if (x != r2) {
            if (x != null) {
                unsetRelationInOneWay(x, d.opposite());
            }
            setRelationInOneWay(r1, d, r2);
            y = getRoom(r2, d.opposite());
        }
        if (y != r1) {
            if (y != null) {
                unsetRelationInOneWay(y, d);
            }
            setRelationInOneWay(r2, d.opposite(), r1);
        }
    }

    /**
     * Installe la porte door entre r et getRoom(r, d).
     * @pre <pre>
     *     r != null && d != null && door != null
     *     getRoom(r, d) != null </pre>
     * @post <pre>
     *     getDoor(r, d) == door </pre>
     */
    public void install(IRoom r, Direction d, IDoor door) {
        if (r == null || d == null || door == null || getRoom(r, d) == null) {
            throw new AssertionError();
        }
        Pair pair = getPairImpl(r, d);
        pair.setDoor(door);

        Pair paire = (Pair)getPairImpl(pair.room(), d.opposite());
        paire.setDoor(door);
    };

    // OUTILS

    /**
     * La salle à laquelle on accède à partir de r, dans la direction d.
     * NB : result peut être null, cela signifie qu'il n'y a pas de salle
     *  associée à r dans la Direction d.
     * @pre
     *     r != null && d != null
     * @post
     *     data.get(r) == null || ((Map) data.get(r)).get(d) == null
     *         ==> result == null
     *     data.get(r) != null && ((Map) data.get(r)).get(d) != null
     *         ==> result == ((Map) data.get(r)).get(d)
     */
    private Pair getPairImpl(IRoom r, Direction d) {
        assert r != null && d != null;

        /*
         * Rappel : m est une Map dont les clés sont de type Direction
         *  et les valeurs de type IRoom
         */
        Map m = (Map) data.get(r);
        Pair result = null;
        if (m != null) {
            result = (Pair) m.get(d);
        }
        return result;
    }



    /**
     * Etablit une relation entre src et dest, dans la direction d en partant
     *  de src.
     * L'invariant n'est pas requis en entrée et n'est pas respecté en sortie !
     * @pre
     *     src != null && d != null && dest != null
     *     getRoom(src, d) == null
     * @post
     *     getRoom(src, d) == dest
     */
    private void setRelationInOneWay(IRoom src, Direction d, IRoom dest) {
        assert src != null && d != null && dest != null;
        assert getPairImpl(src, d) == null;

        Map m = (Map) data.get(src);
        if (m == null) {
            m = new HashMap();
            data.put(src, m);
        }
        m.put(d, new Pair(dest));
    }

    /**
     * Sachant qu'il existe une relation à partir de r dans la direction d,
     *  supprime uniquement cette relation.
     * L'invariant n'est pas requis en entrée et n'est pas respecté en sortie !
     * @pre
     *     r != null && d != null
     *     getRoom(r, d) != null
     * @post
     *     getRoom(r, d) == null
     */
    private void unsetRelationInOneWay(IRoom r, Direction d) {
        assert r != null && d != null;
        assert getRoom(r, d) != null;

        Map m = (Map) data.get(r);
        m.remove(d);
        if (m.isEmpty()) {
            data.remove(r);
        }
    }
}
