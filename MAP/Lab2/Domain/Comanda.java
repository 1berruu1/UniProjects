package Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comanda extends Tort implements Serializable {
    private static final long serialVersionUID = 1L;


    private List<Tort> lista_torturi;
    private Date data;

    public Comanda(){
        super();
    }

    public List<Tort> getLista_torturi() {
        return lista_torturi;
    }

    public void setLista_torturi(List<Tort> lista_torturi) {
        this.lista_torturi = lista_torturi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder detaliiComanda = new StringBuilder("Id comanda: " + id + " Data: " + data + " Lista torturi: ");
        for (Tort tort : lista_torturi) {
            detaliiComanda.append(tort.getTip_tort()).append(", ");
        }
        return detaliiComanda.toString();
    }


    public Comanda(int id, String tip_tort, List<Tort> lista_torturi, Date data) {
        super(id, tip_tort);
        this.lista_torturi = lista_torturi;
        this.data = data;
    }
}

