package com.example.lab4;

import Domain.Comanda;
import Domain.Tort;
import Repository.SQLRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Comanda> comandaTableView;

    @FXML
    private TableColumn<Comanda, Integer> idColumn;

    @FXML
    private TableColumn<Comanda, String> nameColumn;

    @FXML
    private TableColumn<Comanda, String> dateColumn;

    @FXML
    private TableView<Tort> tortTableView;

    @FXML
    private TableColumn<Tort, Integer> tortIdColumn;

    @FXML
    private TableColumn<Tort, String> tortTypeColumn;

    @FXML
    private TableColumn<Comanda, String> cakeColumn;

    @FXML
    private TextField cakeField;

    @FXML
    private TextField orderIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField cakeIdField;

    @FXML
    private TextField cakeTypeField;

    @FXML
    private TabPane tabPane;

    // INITIALIZARE
    protected SQLRepository<Comanda> comandaRepository;
    protected SQLRepository<Tort> tortRepository;

    @FXML
    private void initialize() {
        comandaRepository = new SQLRepository<>(Comanda.class);
        tortRepository = new SQLRepository<>(Tort.class);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        cakeColumn.setCellValueFactory(new PropertyValueFactory<>("lista_torturi"));

        comandaTableView.getItems().addAll(comandaRepository.getAll());

        tortIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tortTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tip_tort"));

        tortTableView.getItems().addAll(tortRepository.getAll());

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab.getText().equals("Orders")) {
                comandaTableView.setVisible(true);
                tortTableView.setVisible(false);
            } else if (newTab.getText().equals("Cakes")) {
                comandaTableView.setVisible(false);
                tortTableView.setVisible(true);
            }
        });
        comandaTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldOrder, newOrder) -> {
            if (newOrder != null) {
                orderIdField.setText(String.valueOf(newOrder.getId()));
                nameField.setText(newOrder.getName());
                dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(newOrder.getData()));
                cakeField.setText(newOrder.getLista_torturi().stream().map(tort -> String.valueOf(tort.getId())).collect(Collectors.joining(",")));
            }
        });

        tortTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldCake, newCake) -> {
            if (newCake != null) {
                cakeIdField.setText(String.valueOf(newCake.getId()));
                cakeTypeField.setText(newCake.getTip_tort());
            }
        });
    }
    //
    //                                      FUNCTII
    //

    @FXML
    private void handleAddOrder() {
        try {
            if (orderIdField.getText().isEmpty() || nameField.getText().isEmpty() || dateField.getText().isEmpty() || cakeField.getText().isEmpty()) {
                showErrorPopup("All fields must be filled.");
                return;
            }
            int id = Integer.parseInt(orderIdField.getText());
            String name = nameField.getText();
            String[] cakeIds = cakeField.getText().split(",");
            List<Tort> cakes = new ArrayList<>();
            for (String cakeId : cakeIds) {
                if (!cakeId.trim().isEmpty()) {
                    try {
                        int parsedCakeId = Integer.parseInt(cakeId.trim());
                        Tort tort = tortRepository.findById(parsedCakeId);
                        if (tort != null) {
                            cakes.add(tort);
                        } else {
                            showErrorPopup("Invalid tort ID: " + parsedCakeId);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        showErrorPopup("Invalid cake ID format: " + cakeId.trim());
                        return;
                    }
                }
            }
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getText());
            Comanda comanda = new Comanda(id, name, cakes, date);
            comandaRepository.add(comanda);
            comandaTableView.getItems().add(comanda);
        } catch (NumberFormatException e) {
            showErrorPopup("Invalid number format: " + e.getMessage());
        } catch (ParseException e) {
            showErrorPopup("Invalid date format: " + e.getMessage());
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    private void handleDeleteOrder() {
        try {
            Comanda selectedOrder = comandaTableView.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                comandaRepository.delete(selectedOrder.getId());
                comandaTableView.getItems().remove(selectedOrder);
            }
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    private void handleUpdateOrder() {
        try {
            Comanda selectedOrder = comandaTableView.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                selectedOrder.setName(nameField.getText());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getText());
                selectedOrder.setData(date);
                comandaRepository.Update(selectedOrder);
                comandaTableView.refresh();
            }
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    private void handleAddCake() {
        try {
            int id = Integer.parseInt(cakeIdField.getText());
            String type = cakeTypeField.getText();
            Tort tort = new Tort(id, type);
            tortRepository.add(tort);
            tortTableView.getItems().add(tort);
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    private void handleDeleteCake() {
        try {
            Tort selectedCake = tortTableView.getSelectionModel().getSelectedItem();
            if (selectedCake != null) {
                tortRepository.delete(selectedCake.getId());
                tortTableView.getItems().remove(selectedCake);
            }
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    @FXML
    private void handleUpdateCake() {
        try {
            Tort selectedCake = tortTableView.getSelectionModel().getSelectedItem();
            if (selectedCake != null) {
                selectedCake.setTip_tort(cakeTypeField.getText());
                tortRepository.Update(selectedCake);
                tortTableView.refresh();
            }
        } catch (Exception e) {
            showErrorPopup(e.getMessage());
        }
    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    //
    //                                    Rapoarte
    //

    public Map<String, Integer> displayCakesOrderedPerDay() {
        Map<String, Integer> cakesPerDay = new HashMap<>();
        for (Comanda comanda : comandaRepository.getAll()) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(comanda.getData());
            cakesPerDay.put(date, cakesPerDay.getOrDefault(date, 0) + comanda.getLista_torturi().size());
        }
        return cakesPerDay.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Integer> displayCakesOrderedPerMonth() {
        Map<String, Integer> cakesPerMonth = new HashMap<>();
        for (Comanda comanda : comandaRepository.getAll()) {
            String month = new SimpleDateFormat("yyyy-MM").format(comanda.getData());
            cakesPerMonth.put(month, cakesPerMonth.getOrDefault(month, 0) + comanda.getLista_torturi().size());
        }
        return cakesPerMonth.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<Tort, Integer> displayMostOrderedCakes() {
        Map<Tort, Integer> cakeOrders = new HashMap<>();
        for (Comanda comanda : comandaRepository.getAll()) {
            for (Tort tort : comanda.getLista_torturi()) {
                cakeOrders.put(tort, cakeOrders.getOrDefault(tort, 0) + 1);
            }
        }
        return cakeOrders.entrySet()
                .stream()
                .sorted(Map.Entry.<Tort, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    @FXML
    public void handleCakesOrderedPerDay(ActionEvent actionEvent) {
        Map<String, Integer> cakesPerDay = displayCakesOrderedPerDay();
        StringBuilder result = new StringBuilder();
        cakesPerDay.forEach((date, count) -> result.append("Date: ").append(date).append(", Cakes Ordered: ").append(count).append("\n"));
        openResultWindow("cakes-per-day-view.fxml", result.toString());
    }

    @FXML
    public void handleCakesOrderedPerMonth(ActionEvent actionEvent) {
        {
            Map<String, Integer> cakesPerMonth = displayCakesOrderedPerMonth();
            StringBuilder result = new StringBuilder();
            cakesPerMonth.forEach((month, count) -> result.append("Month: ").append(month).append(", Cakes Ordered: ").append(count).append("\n"));
            openResultWindow("cakes-per-month-view.fxml", result.toString());
        }

    }

    @FXML
    private void handleMostOrderedCakes() {
        Map<Tort, Integer> cakeOrders = displayMostOrderedCakes();
        StringBuilder result = new StringBuilder();
        cakeOrders.forEach((tort, count) -> result.append("Cake ID: ").append(tort.getId()).append(", Type: ").append(tort.getTip_tort()).append(", Ordered: ").append(count).append(" times\n"));
        openResultWindow("most-ordered-cakes-view.fxml", result.toString());
    }

    private void openResultWindow(String title, String result) {
        Stage stage = new Stage();
        stage.setTitle(title);
        TextArea textArea = new TextArea(result);
        textArea.setEditable(false);
        VBox vbox = new VBox(textArea);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}