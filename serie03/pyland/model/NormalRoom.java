package pyland.model;

public class NormalRoom extends Room implements INormalRoom {

    /**
     * @post <pre>
     *     Let x ::= old v.getPowerLevel()
     *     getVisitor().getPowerLevel() == max(0, x - POWER_LOSS) </pre>
     */
    public void setVisitor(IPlayer v) {
        int x = v.getPowerLevel();
        v.setLessPowerful(POWER_LOSS);
        super.setVisitor(v);
    }


    /**
     * Une chaîne décrivant l'effet feng shui de cette salle sur son visiteur.
     */
    @Override
    public String fengShuiEffect() {
        return "";
    }
}
