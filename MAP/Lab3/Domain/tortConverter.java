package Domain;

public class TortConverter extends AbstractConverter<Tort> {

    @Override
    public String toString(Tort tort){
        return tort.getId()+ ";" + tort.getTip_tort();
    }

    @Override
    public Tort fromString(String string) {
        String[] tokens = string.split(";");
        int id = Integer.parseInt(tokens[0]);
        String tip_tort = tokens[1];
        return new Tort(id, tip_tort);
    }

}


