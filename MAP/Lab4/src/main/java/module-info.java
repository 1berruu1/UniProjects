module com.example.lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.lab4 to javafx.fxml;
    opens Domain to javafx.base;
    exports com.example.lab4;
}