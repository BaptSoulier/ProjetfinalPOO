package pyland.model;

public class ExitRoom extends NormalRoom implements IExitRoom{


    /**
     * @post <pre>
     *     getVisitor().hasLeft()
     *     fengShuiEffect().contains("Vous êtes sorti des griffes de"
     *             + " PY le maléfique !") </pre>
     */
    public void setVisitor(IPlayer v) {
        v.leave();
        super.setVisitor(v);
        fengShuiEffect();
    }


    @Override
    public String fengShuiEffect() {
        return "Vous êtes sorti des griffes de PY le maléfique !";
    }
}
