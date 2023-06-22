package pyland.model;

public class MagicRoom extends Room implements IMagicRoom{

    /**
     * @post <pre>
     *     Let x ::= old v.getPowerLevel()
     *     x + MIN <= getVisitor().getPowerLevel() <= x + MAX
     *     fengShuiEffect().contains("Vous sentez un flot d'énergie positive"
     *             + " vous envahir.") </pre>
     */
    public void setVisitor(IPlayer v) {
        fengShuiEffect();
        int x = v.getPowerLevel();
        int nbrPower = (int)(Math.random() * MAX) + MIN;
        v.setMorePowerful(nbrPower);
        super.setVisitor(v);
    }


    /**
     * Une chaîne décrivant l'effet feng shui de cette salle sur son visiteur.
     */
    @Override
    public String fengShuiEffect() {
        return "Vous sentez un flot d'énergie positive vous envahir.";
    }
}
