package Domain;

import java.io.Serializable;

public class Tort extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String tip_tort;

    public String getTip_tort() {
        return tip_tort;
    }

    public void setTip_tort(String tip_tort) {
        this.tip_tort = tip_tort;
    }

    public Tort() {
    }


    @Override
    public String toString() {
        return "Id Tort=" + getId() + ", Tip Tort= " + getTip_tort() + " ";
    }

    public Tort(int id, String tip_tort) {
        super(id);
        this.tip_tort = tip_tort;
    }
}
