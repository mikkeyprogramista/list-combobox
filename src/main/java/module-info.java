module com.adz1q.listcombobox {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adz1q.listcombobox to javafx.fxml;
    exports com.adz1q.listcombobox;
}