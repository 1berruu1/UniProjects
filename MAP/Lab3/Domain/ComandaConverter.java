package Domain;

import Repository.RepositoryException;
import Service.TortService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComandaConverter extends AbstractConverter<Comanda> {
    private TortService tortService;

    public ComandaConverter(TortService tortService){
        this.tortService = tortService;
    }

    @Override
    public String toString(Comanda comanda) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(comanda.getData());
        List<Tort> tortList = comanda.getLista_torturi();
        StringBuilder tortListStr = new StringBuilder();
        for (Tort tort : tortList){
            tortListStr.append(tort.getId()).append(",");
        }

        if (tortListStr.length() > 0){
            tortListStr.deleteCharAt(tortListStr.length() - 1);
        }
        return comanda.getId() + ";" + comanda.getData() + ";" + dateStr + ";" + tortListStr.toString();
    }

    @Override
    public Comanda fromString(String string) throws ParseException {
        String[] tokens = string.split(";");
        int id = Integer.parseInt(tokens[0]);
        String nume = tokens[1];
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[2]);
        List<Tort> tortList = new ArrayList<>();
        if(tokens.length > 3){
            String[] tortIDs = tokens[3].split(",");
            for(String tortID : tortIDs){
                int idTort = Integer.parseInt(tortID);
                Tort tort = tortService.findById(idTort);
                if(tort != null){
                    tortList.add(tort);
                }

            }
        }
        return new Comanda(id, nume, tortList, date);
    }
}
