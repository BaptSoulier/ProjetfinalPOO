package pyland.model;

public class Player implements IPlayer{

    private IRoom salle;
    private boolean left;
    private int lifePoints;
    private int power;

    private Key cle;

    public Player(int HP) {
        this.lifePoints = HP;
    }


    /**
     * La salle dans laquelle se trouve le joueur.
     */
    public IRoom getLocation() {
        return this.salle;
    }

    /**
     * Indique si le joueur a arrêté la partie.
     */
    public boolean hasLeft() {
        return this.left;
    }

    /**
     * Fait s'arrêter le joueur.
     * @pre <pre>
     *     !hasLeft() </pre>
     * @post <pre>
     *     hasLeft()
     *     getLocation() == old getLocation()
     *     getPowerLevel() == old getPowerLevel()
     *     getHitPoints() == old getHitPoints()</pre>
     */
    public void leave() {
        if (!hasLeft()) {
            this.left = true;
            this.salle = getLocation();
            this.lifePoints = getHitPoints();
            this.power = getPowerLevel();
        }

    }

    /**
     * Associe la salle <code>r</code> à ce joueur.
     * @pre <pre>
     *     !hasLeft()
     *     getLocation() == null && r != null
     *     r.getVisitor() == null || r.getVisitor() == this </pre>
     * @post <pre>
     *     getLocation() == r
     *     l'état du joueur peut avoir changé selon le type de r </pre>
     */
    public void setLocation(IRoom r) {
        if ((!hasLeft()) && (getLocation() == null) && (r != null) && (r.getVisitor() == null || r.getVisitor() == this)){
            r.setVisitor(this);
            this.salle = r;
        }
    }

    /**
     * Dissocie ce joueur de sa salle.
     * @pre <pre>
     *     !hasLeft()
     *     getLocation() != null </pre>
     * @post <pre>
     *     getLocation() == null </pre>
     */
    public void unsetLocation() {
        if ((!hasLeft()) && (getLocation() != null)) {
            this.salle.unsetVisitor();
            this.salle = null;
        }
    }

    /**
     * La quantité de superpouvoir du joueur.
     */
    @Override
    public int getPowerLevel() {
        return this.power;
    }

    /**
     * Transforme ce joueur en super héro pour q tours.
     * @pre <pre>
     *     q >= 0
     *     !hasLeft() </pre>
     * @post <pre>
     *     !hasLeft()
     *     getLocation() == old getLocation()
     *     getHitPoints() == old getHitPoints()
     *     getPowerLevel() == old getPowerLevel() + q </pre>
     */
    public void setMorePowerful(int q) {
        if ((q >= 0) && (!hasLeft())) {
            this.power += q;
            this.salle = getLocation();
            this.lifePoints = getHitPoints();
        }
    }

    /**
     * Fait perdre des super pouvoirs au joueur.
     * @pre <pre>
     *     getPowerLevel() >= q >= 0
     *     !hasLeft() </pre>
     * @post <pre>
     *     !hasLeft()
     *     getLocation() == old getLocation()
     *     getHitPoints() == old getHitPoints()
     *     getPowerLevel() == old getPowerLevel() - q </pre>
     */
    public void setLessPowerful(int q) {
        if ((getPowerLevel() >= q) && (q >= 0) && (!hasLeft())){
            this.power -= q;
            this.salle = getLocation();
            this.lifePoints = getHitPoints();
        }
    }

    /**
     * Le nombre de points d'attaque de ce joueur.
     */
    public int getHitPoints() {
        return this.lifePoints;
    }

    /**
     * Indique si le joueur est mort.
     */
    public boolean isDead() {
        if (getHitPoints() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Augmente le nombre de points d'attaque de ce joueur.
     * @pre <pre>
     *     q >= 0
     *     !hasLeft() </pre>
     * @post <pre>
     *     !hasLeft()
     *     getLocation() == old getLocation()
     *     getPowerLevel() == old getPowerLevel()
     *     getHitPoints() == old getHitPoints() + q </pre>
     */
    public void strengthen(int q) {
       if (q >= 0 && !hasLeft()) {
           this.salle = getLocation();
           this.power = getPowerLevel();
           this.lifePoints += q;
       }
    }

    /**
     * Tue le joueur.
     * @pre
     *     !hasLeft()
     * @post <pre>
     *     getLocation() == old getLocation()
     *     getPowerLevel() == old getPowerLevel()
     *     getHitPoints() == 0
     *     hasLeft() </pre>
     */
    public void kill() {
        if (!hasLeft()) {
            this.lifePoints = 0;
            this.left = true;
            this.salle = getLocation();
            this.power = getPowerLevel();
        }
    }

    /**
     * Donne la clé du joueur.
     */
    @Override
    public Key getKey() {
        return cle;
    }

    /**
     * Donne la clé au joueur.
     * @pre <pre>
     *     !hasLeft()
     *     k != null </pre>
     * @post <pre>
     *     !hasLeft()
     *     getLocation() == old getLocation()
     *     getPowerLevel() == old getPowerLevel()
     *     getHitPoints() == old getHitPoints()
     *     getKey() == k </pre>
     */
    public void pickUpKey(Key k) {
        this.cle = k;
    };
}
