package Domain;

import java.io.Serializable;

public class Tort extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String tip_tort;
    protected  int id_comanda;

    public String getTip_tort() {
        return tip_tort;
    }

    public void setTip_tort(String tip_tort) {
        this.tip_tort = tip_tort;
    }


    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    @Override
    public String toString() {
        return "Id Tort=" + getId() + ", Tip Tort= " + getTip_tort() + " ";
    }

    public Tort() {
    }
    public Tort(int id, String tip_tort) {
        super(id);
        this.tip_tort = tip_tort;
    }
}
