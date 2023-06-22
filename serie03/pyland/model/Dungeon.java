package pyland.model;

public class Dungeon extends NormalRoom implements IDungeon {

    private boolean Princesse;

    public Dungeon() {
        this.Princesse = true;
    }
    /**
     * Indique si la princesse est retenue captive ou non.
     */
    public boolean isPrincessCaptive() {
        return Princesse;
    };

    // COMMANDES

    /**
     * @post <pre>
     *     old isPrincessCaptive()
     *         ==> fengShuiEffect().contains("Vous avez libéré la princesse !")
     *     !isPrincessCaptive() </pre>
     */
    public void setVisitor(IPlayer v) {
        this.Princesse = false;
        fengShuiEffect();
        super.setVisitor(v);
    };

    /**
     * @post <pre>
     *     isPrincessCaptive() == old isPrincessCaptive() </pre>
     */
    public void unsetVisitor() {
        super.unsetVisitor();
    };

    public String fengShuiEffect() {
        return "Vous avez libéré la princesse !";
    };

}
