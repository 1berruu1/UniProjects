package Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Comanda extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;



    private List<Tort> lista_torturi;
    private String name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comanda(int id, String nume, List<Tort> lista_torturi, Date data) {
        super(id);
        this.name = nume;
        this.lista_torturi = lista_torturi;
        this.data = data;
    }
}
