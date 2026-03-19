package com.adz1q.listcombobox;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAge;

    @FXML
    private ComboBox<Department> cbxGroup;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnClose;

    @FXML
    private ListView<Student> list;

    private ObservableList<Student> students;

    @Override
    public void initialize(
            URL url,
            ResourceBundle resourceBundle) {
        students = FXCollections.observableArrayList(
                new Student("Mikołaj", "Trojanowski", Department.BT2, 67),
                new Student("Wojciech", "Jabłonowski", Department.BT5, 60));
        list.setItems(students);
        cbxGroup.getItems().addAll(Department.values());

        btnAdd.setOnAction(actionEvent -> addStudent());
        btnRemove.setOnAction(actionEvent -> removeStudent());
        btnClose.setOnAction(actionEvent -> Platform.exit());
    }

    private void addStudent() {
        if (txtFirstName.getText().isBlank()
                || txtLastName.getText().isBlank()
                ||  txtAge.getText().isBlank()) {
            return;
        }

        var firstName = txtFirstName.getText().trim();
        var lastName = txtLastName.getText().trim();
        var department = cbxGroup.getValue();
        var age = Integer.parseInt(txtAge.getText().trim());

        var student = new Student(
                firstName,
                lastName,
                department,
                age);

        students.add(student);
        showAlert(
                Alert.AlertType.INFORMATION,
                "Dodawanie ucznia",
                student.lastName(),
                "Poprawnie dodano ucznia do listy");
    }

    private void removeStudent() {
        var student = list.getSelectionModel()
                .getSelectedItem();
        if (student == null) return;
        students.remove(student);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Usuwania ucznia",
                student.lastName(),
                "Poprawnie usunięto ucznia z listy");
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String header,
            String content) {
        var alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private record Student(
            String firstName,
            String lastName,
            Department department,
            int age) {
        @Override
        public String toString() {
            return new StringBuilder()
                    .append(firstName)
                    .append(" ")
                    .append(lastName)
                    .append("\n")
                    .append(age)
                    .append(" lat ")
                    .append(department.name)
                    .toString();
        }
    }

    private enum Department {
        BT1 ("1BT"),
        BT2 ("2BT"),
        BT3 ("3BT"),
        BT4 ("4BT"),
        BT5 ("5BT");

        protected final String name;

        Department(String name) {
            this.name = name;
        }
    }
}
