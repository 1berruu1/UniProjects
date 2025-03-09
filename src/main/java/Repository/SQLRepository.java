package Repository;

import Domain.Comanda;
import Domain.Entity;
import Domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class SQLRepository<T extends Entity> extends Repository<T> {
    Connection connection;
    private Class<T> entityClass;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    private String DB_URL = "jdbc:sqlite:C:\\Users\\crist\\IdeaProjects\\Lab4\\src\\main\\java\\Bakery.db";

    public SQLRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        openConnection();
        createTable();
        loadData();
    }

    private void loadData() {
        try {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Torturi");
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Tort tort = new Tort(rs.getInt("id"), rs.getString("tip_tort"));
                    items.add((T) tort);
                }
            }

            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Comanda");
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tip_tort = rs.getString("tip_tort");
                    String data = rs.getString("data");
                    List<Tort> lista_torturi = getTorturiByIds(tip_tort);
                    try {
                        Date parsedDate = dateFormat.parse(data);
                        Comanda comanda = new Comanda(id, tip_tort, lista_torturi, new java.sql.Date(parsedDate.getTime()));
                        items.add((T) comanda);
                    } catch (ParseException e) {
                        System.out.println("Error parsing date: " + data);
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void openConnection() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(DB_URL);
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            System.out.println("Eroare la crearea conexiuni" + e);
        }

    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createTable() {
        String createComandaTable = "CREATE TABLE IF NOT EXISTS Comanda (" +
                "id INTEGER PRIMARY KEY, " +
                "nume VARCHAR(50)," +
                "data VARCHAR(50)," +
                "tip_tort VARCHAR(50))";

        String createTorturiTable = "CREATE TABLE IF NOT EXISTS Torturi (" +
                "id INTEGER PRIMARY KEY, " +
                "tip_tort VARCHAR(50))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTorturiTable);
            statement.execute(createComandaTable);
        } catch (SQLException e) {
            System.out.println("Error at creating table" + e);
        }
    }

    //
    //                                  UPDATE FUNCTIONS
    //
    public void Update(T item) {
        if (item instanceof Tort) {
            updateTort((Tort) item);
        } else if (item instanceof Comanda) {
            updateComanda((Comanda) item);
        }
    }

    private void updateComanda(Comanda item) {
        String sql = "UPDATE Comanda SET nume = ?, data = ?, tip_tort = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getData().toString());
            String tortIds = item.getLista_torturi().stream()
                    .map(tort -> String.valueOf(tort.getId()))
                    .collect(Collectors.joining(","));
            statement.setString(3, tortIds);
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating comanda" + e);
        }

    }

    private void updateTort(Tort item) {
        String sql = "UPDATE Torturi SET tip_tort = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getTip_tort());
            statement.setInt(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating tort" + e);
        }

    }

    //
    //                                      ADD FUNCTIONS
    //
    public void add(T entity) throws RepositoryException {

        if (entity instanceof Tort) {
            addTort((Tort) entity);
        } else if (entity instanceof Comanda) {
            addComanda((Comanda) entity);
        } else {
            throw new RepositoryException("Unsupported entity type");
        }
    }

    private void addComanda(Comanda comanda) throws RepositoryException {

        for (Tort tort : comanda.getLista_torturi()) {
            if (findById(tort.getId()) == null) {
                throw new RepositoryException("Tort with ID " + tort.getId() + " does not exist");
            }
        }
        String sql = "INSERT INTO Comanda (id, nume, data, tip_tort) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, comanda.getId());
            statement.setString(2, comanda.getName());
            statement.setString(3, comanda.getData().toString());
            String tortIds = comanda.getLista_torturi().stream()
                    .map(tort -> String.valueOf(tort.getId()))
                    .collect(Collectors.joining(","));
            statement.setString(4, tortIds);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Error adding comanda", e);
        }
    }


    private void addTort(Tort tort) throws RepositoryException {
        if (findById(tort.getId()) != null) {
            throw new RepositoryException("Tort with ID " + tort.getId() + " already exists");
        }

        String sql = "INSERT INTO Torturi (id, tip_tort) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tort.getId());
            statement.setString(2, tort.getTip_tort());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Error adding tort", e);
        }
    }

    //
    //                                  GET FUNCTIONS
    //
    @Override
    public List<T> getAll() {
        try {
            if (Tort.class.isAssignableFrom(entityClass)) {
                return (List<T>) getAllTort();
            } else if (Comanda.class.isAssignableFrom(entityClass)) {
                return (List<T>) getAllComenzi();
            } else {
                throw new RepositoryException("Unsupported entity type");
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Comanda> getAllComenzi() throws RepositoryException {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM Comanda";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String data = rs.getString("data");
                String tip_tort = rs.getString("tip_tort");
                List<Tort> lista_torturi = getTorturiByIds(tip_tort);
                Date parsedDate = dateFormat.parse(data);
                Comanda comanda = new Comanda(id, nume, lista_torturi, new java.sql.Date(parsedDate.getTime()));
                comenzi.add(comanda);
            }
        } catch (SQLException | ParseException e) {
            throw new RepositoryException("Error retrieving all comenzi", e);
        }
        return comenzi;
    }

    public List<Tort> getAllTort() throws RepositoryException {
        List<Tort> torturi = new ArrayList<>();
        String sql = "SELECT * FROM Torturi";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String tip_tort = rs.getString("tip_tort");
                Tort tort = new Tort(id, tip_tort);
                torturi.add(tort);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error retrieving all torturi", e);
        }
        return torturi;
    }

    private List<Tort> getTorturiByIds(String tortIds) {
        List<Tort> torturi = new ArrayList<>();
        String[] ids = tortIds.split(",");
        for (String id : ids) {
            try {
                int tortId = Integer.parseInt(id);
                Tort tort = (Tort) findById(tortId);
                if (tort != null) {
                    torturi.add(tort);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid tort ID: " + id);
            }
        }
        return torturi;
    }

    //
    //                                  REMOVE FUNCTIONS
    //
    public void delete(int id) {
        try {
            if (Tort.class.isAssignableFrom(entityClass)) {
                removeTort(id);
            } else if (Comanda.class.isAssignableFrom(entityClass)) {
                removeComanda(id);
            } else {
                throw new RepositoryException("Unsupported entity type");
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    private void removeComanda(int id) {
        String sql = "DELETE FROM Comanda WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing comanda" + e);
        }
    }

    private void removeTort(int id) {
        String deleteTortSql = "DELETE FROM Torturi WHERE id = ?";
        String updateComandaSql = "UPDATE Comanda SET tip_tort = REPLACE(tip_tort, ?, '') WHERE tip_tort LIKE ?";

        try (PreparedStatement deleteTortStmt = connection.prepareStatement(deleteTortSql);
             PreparedStatement updateComandaStmt = connection.prepareStatement(updateComandaSql)) {


            deleteTortStmt.setInt(1, id);
            deleteTortStmt.executeUpdate();

            String tortIdStr = id + ",";
            updateComandaStmt.setString(1, tortIdStr);
            updateComandaStmt.setString(2, "%" + tortIdStr + "%");
            updateComandaStmt.executeUpdate();

            updateComandaStmt.setString(1, "," + tortIdStr);
            updateComandaStmt.setString(2, "%" + "," + tortIdStr + "%");
            updateComandaStmt.executeUpdate();

            updateComandaStmt.setString(1, tortIdStr);
            updateComandaStmt.setString(2, tortIdStr + "%");
            updateComandaStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error removing tort" + e);
        }
    }
}
