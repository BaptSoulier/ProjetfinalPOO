package pyland.model;

public class MonsterRoom extends NormalRoom implements IMonsterRoom {

    private int nbrMonstres;
    private int shui;

    public MonsterRoom(int nbrMonstres) {
        super();
        this.nbrMonstres = nbrMonstres;
    }

    /**
     * @post <pre>
     *     Let x ::= old monstersNb()
     *         y ::= old v.getHitPoints()
     *     0 < x <= y
     *        ==> monstersNb() == 0
     *            getVisitor().getHitPoints() == y + x / 2
     *            fengShuiEffect().contains("Vous combattez victorieusement !")
     *     x > y
     *        ==> monstersNb() == x + y / 2
     *            getVisitor().isDead()
     *            fengShuiEffect().contains("Vous succombez"
     *                    + " dans un râle affreux...") </pre>
     */
    public void setVisitor(IPlayer v) {
        int x = monstersNb();
        int y = v.getHitPoints();
        if ((y >= x) && (x > 0)) {
            this.nbrMonstres = 0;
            v.strengthen(x/2);
            this.shui = 1;
        } else if (x > y) {
            this.nbrMonstres = x+y/2;
            v.kill();
            this.shui = 2;
        }
        super.setVisitor(v);
        fengShuiEffect();
    }

    /**
     * @post <pre>
     *     monstersNb() == old monstersNb() </pre>
     */
    public void unsetVisitor() {
        super.unsetVisitor();
    }

    /**
     * Le nombre de monstres présents dans cette salle.
     */
    public int monstersNb() {
        return this.nbrMonstres;
    }

    /**
     * Une chaîne décrivant l'effet feng shui de cette salle sur son visiteur.
     */
    @Override
    public String fengShuiEffect() {
        if (this.shui == 1) {
            return "Vous combattez victorieusement !";
        } else if (this.shui == 2) {
            return "Vous succombez dans un râle affreux...";
        }
        return null;
    }
}
