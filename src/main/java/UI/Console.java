package UI;

import Domain.Comanda;
import Domain.Entity;
import Domain.Tort;
import Repository.RepositoryException;
import Service.ComandaService;
import Service.TortService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Console {
    private ComandaService comandaService;
    private TortService tortService;
    private Scanner scanner;

    public Console(ComandaService comandaService, TortService tortService) {
        this.comandaService = comandaService;
        this.tortService = tortService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            try{
                switch (option) {
                    case 1:
                        addTort();
                        break;
                    case 2:
                        createComanda();
                        break;
                    case 3:
                        printComanda();
                        break;
                    case 4:
                        updateTort();
                        break;
                    case 5:
                        updateComanda();
                        break;
                    case 6:
                        deleteTort();
                        break;
                    case 7:
                        deleteComanda();
                        break;
                    case 8:
                        refreshData();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }catch (RepositoryException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void refreshData() throws RepositoryException {
            List<Tort> cakes = tortService.getAllTort();
            List<Comanda> comenzi = comandaService.getAllComenzi();
            System.out.println("Datele au fost reactualizate");
    }

    private void deleteComanda() {
        try {
            System.out.println("Introduceti id-ul pe care il veti sterge");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean found = false;
            for (Comanda comanda : comandaService.getAllComenzi()) {
                if (comanda.getId() == id) {
                    comandaService.deleteComanda(id);
                    found = true;
                    System.out.println("Comanda a fost stearsa");
                    break;
                }
            }
            if (!found) {
                System.out.println("Comanda nu a fost gasita");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteTort() {
        try {
            System.out.println("Introduceti id-ul care va fi sters");
            int id = scanner.nextInt();
            scanner.nextLine();
            boolean found = false;

            for (Tort tort : tortService.getAllTort()) {
                if (tort.getId() == id) {
                    tortService.deleteTort(id);
                    found = true;
                    System.out.println("Tortul a fost sters");
                    break;
                }
            }
            if (!found) {
                System.out.println("Tortul nu a fost gasit");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateComanda() {
        try {
            System.out.println("Introduceti id-ul comenzii pe care doriti sa il modificati: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (Comanda comanda : comandaService.getAllComenzi()){
                if(comanda.getId() == id){
                    System.out.println("Introduceti noul nume al comenzii: ");
                    String name = scanner.nextLine();
                    System.out.println("Introduceti noua data a comenzii(yyyy-MM-dd): ");
                    String dateString = scanner.nextLine();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                    List<Tort> tortList = new ArrayList<>();
                    while (true) {
                        System.out.print("Introdu id-ul tortului care vrei sa il adaugi la comanda (sau apasa 0 pt a termina): ");
                        int tortId = scanner.nextInt();
                        scanner.nextLine();
                        if (tortId == 0) break;

                        Tort tort = tortService.findById(tortId);
                        if (tort != null) {
                            tortList.add(tort);
                        } else {
                            System.out.println("Tortul nu a fost gasit.");
                        }
                    }
                    comandaService.updateComanda(id, name, tortList, date);
                    System.out.println("Comanda a fost updatata");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTort() {
        try {
            System.out.println("Introduceti id-ul tortului care va fi updatat: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Tort tort : tortService.getAllTort()) {
                if (tort.getId() == id) {
                    System.out.println("Introduceti noul tip de tort: ");
                    String tip_tort = scanner.nextLine();
                    tortService.UpdateTort(id, tip_tort);
                    System.out.println("Tortul a fost updatat");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void printComanda() {
        try{
            for(Comanda comanda : comandaService.getAllComenzi()){
                System.out.println(comanda);
            }
        } catch (Exception e) {
            System.out.println("oh!" + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Adauga Tort");
        System.out.println("2. Adauga Comanda");
        System.out.println("3. Printeaza Comenzi");
        System.out.println("4. Update Tort");
        System.out.println("5. Update Comanda");
        System.out.println("6. Delete Tort");
        System.out.println("7. Delete Comanda");
        System.out.println("8. Refresh Data");
        System.out.println("0. Exit");
        System.out.println("================");
        System.out.println("Introduceti optiunea: ");
    }

    public void addTort() throws RepositoryException {
        try{
            System.out.println("Introduceti id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Introduceti tip tort: ");
            String tip_tort = scanner.nextLine();
            tortService.addTort(id, tip_tort);
        }catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createComanda() throws RepositoryException {
        try {
            System.out.print("Introduce ID-ul comenzii: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Introdu numele comenzii: ");
            String name = scanner.nextLine();
            System.out.print("Introdu data comenzii in forma (yyyy-mm-dd): ");
            String dateString = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

            List<Tort> tortList = new ArrayList<>();
            while (true) {
                System.out.print("Introdu id-ul tortului care vrei sa il adaugi la comanda (sau apasa 0 pt a termina): ");
                int tortId = scanner.nextInt();
                scanner.nextLine();
                if (tortId == 0) break;

                Tort tort = tortService.findById(tortId);
                if (tort != null) {
                    tortList.add(tort);
                } else {
                    System.out.println("Tortul nu a fost gasit.");
                }
            }
            comandaService.addComanda(id, name, tortList, date);
            System.out.println("Comanda a fost creata cu succes.");
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Data introdusa nu este valida.");
        }
    }
}
