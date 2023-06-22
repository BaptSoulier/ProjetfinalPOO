package pyland.model;

public class Room implements IRoom {

    private IPlayer joueur;

    /**
     * Le joueur qui se trouve dans cette salle.
     */
    public IPlayer getVisitor() {
        return this.joueur;
    }

    /**
     * Associe le visiteur <code>v</code> à cette salle.
     * @pre <pre>
     *     getVisitor() == null
     *     v != null && !v.hasLeft()
     *     v.getLocation() == null || v.getLocation() == this </pre>
     * @post <pre>
     *     getVisitor() == v
     *     fengShuiEffect() reflète l'effet feng shui sur getVisitor() </pre>
     */
    public void setVisitor(IPlayer v) {
        if ((getVisitor() == null) && (v != null) && (!v.hasLeft()) && ((v.getLocation() == null) || (v.getLocation() == this))) {
            this.joueur = v;
        }
    }

    /**
     * Dissocie cette salle de son visiteur.
     * @pre <pre>
     *     getVisitor() != null
     *     !getVisitor().hasLeft() </pre>
     * @post <pre>
     *     getVisitor() == null
     *     fengShuiEffect().equals("") </pre>
     */
    public void unsetVisitor() {
        if ((getVisitor() != null) && !getVisitor().hasLeft()) {
            this.joueur = null;
        }
    }

    /**
     * Une chaîne décrivant l'effet feng shui de cette salle sur son visiteur.
     */
    @Override
    public String fengShuiEffect() {
        return "";
    }
}
