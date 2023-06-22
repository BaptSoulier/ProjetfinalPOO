package pyland.model;

public class DoomRoom extends Room implements IDoomRoom{
    private int shui;
    /**
     * @post <pre>
     *     getVisitor().getPowerLevel() == 0
     *     (old v.getPowerLevel() == 0)
     *         ==> getVisitor().isDead()
     *             fengShuiEffect().contains("Vous mourez dans d'atroces"
     *                     + " souffrances.")
     *     (old v.getPowerLevel() > 0)
     *         ==> !getVisitor().isDead()
     *             fengShuiEffect().contains("Vous sentez que seul"
     *                     + " un super héros pourra sortir d'ici.") </pre>
     */
    public void setVisitor(IPlayer v) {
        int power = v.getPowerLevel();
        v.setLessPowerful(v.getPowerLevel());
        if (power == 0) {
            v.kill();
            this.shui = 1;
        } else if (power > 0) {
            this.shui = 2;
        }
        super.setVisitor(v);
        fengShuiEffect();
    }
    @Override
    public String fengShuiEffect() {
        if (this.shui == 1) {
            return "Vous mourez dans d'atroces souffrances.";
        } else if (this.shui == 2) {
            return "Vous sentez que seul un super héros pourra sortir d'ici.";
        }
        return null;
    }

}
