package pyland.model;

/**
 * @cons <pre>
 *     $POST$
 *         getVisitor() == null
 *         isPrincessCaptive() </pre>
 */
public interface IDungeon extends INormalRoom {

    // REQUETES

    /**
     * Indique si la princesse est retenue captive ou non.
     */
    boolean isPrincessCaptive();

    // COMMANDES

    /**
     * @post <pre>
     *     old isPrincessCaptive()
     *         ==> fengShuiEffect().contains("Vous avez libéré la princesse !")
     *     !isPrincessCaptive() </pre>
     */
    void setVisitor(IPlayer v);

    /**
     * @post <pre>
     *     isPrincessCaptive() == old isPrincessCaptive() </pre>
     */
    void unsetVisitor();
}
