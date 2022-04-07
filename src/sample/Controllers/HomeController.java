package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Models.BasicModel.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    public Menu mnAddEdit;
    public Menu mnView;
    public Menu mnFunction;
    public MenuItem mniStaff;
    public MenuItem mniPatient;
    public MenuItem mniViewPatient;
    public MenuItem mniPrescription;
    public MenuItem mniMedicinesAdministration;
    public MenuItem mniChangePatientBed;
    public Button btnLogout;
    public Button btnChangePassword;
    public Label lblPhone;
    public Label lblName;
    public Label lblType;


    FXMLLoader loader;
    Employee employee;

    /*
     * the Employee properties will assign to the Employee her to record what he does
     * */
    public void initValue(Employee employee){
        this.employee=employee;
        lblType.setText(employee.getType());
        lblName.setText(employee.getName());
        lblPhone.setText(employee.getPhone());
        setMenuDisable(this.employee);
    }

    void setMenuDisable(Employee employee){
        if (employee.getType().equals("Manager")){
            mnFunction.setDisable(true);
            mnView.setDisable(true);
        }
        else if (employee.getType().equals("Doctor")){
            mnAddEdit.setDisable(true);
            mniMedicinesAdministration.setDisable(true);
            mniChangePatientBed.setDisable(true);
        }
        else if (employee.getType().equals("Nurse")){
            mnAddEdit.setDisable(true);
            mniPrescription.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mniStaff.setOnAction(actionEvent -> {
            try { showManagerStaff(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mniPatient.setOnAction(actionEvent -> {
            try { showManagerPatient(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mniViewPatient.setOnAction(actionEvent -> {
            try { showViewPatient(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mniPrescription.setOnAction(actionEvent -> {
            try { showPrescription(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mniMedicinesAdministration.setOnAction(actionEvent -> {
            try { showMedicinesAdministration(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mniChangePatientBed.setOnAction(actionEvent -> {
            try { showChangePatientBed(); } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnChangePassword.setOnAction(actionEvent -> { showChangePassword(actionEvent);
        });

        btnLogout.setOnAction(actionEvent -> {
            try { logout(actionEvent); } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    public void showManagerStaff() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/ManagerStaff.fxml"));
        Parent root1 = (Parent) loader.load();
        ManagerStaffController managerStaffController=loader.getController();
        managerStaffController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showManagerPatient() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/ManagerPatient.fxml"));
        Parent root1 = (Parent) loader.load();
        ManagerPatientController managerPatientController=loader.getController();
        managerPatientController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void showViewPatient() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/ViewPatient.fxml"));
        Parent root1 = (Parent) loader.load();
        ViewPatientController viewPatientController=loader.getController();
        viewPatientController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showPrescription() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/DoctorPrescription.fxml"));
        Parent root1 = (Parent) loader.load();
        DoctorPrescriptionController doctorPrescriptionController=loader.getController();
        doctorPrescriptionController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showMedicinesAdministration() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/NurseMedicinesAdministration.fxml"));
        Parent root1 = (Parent) loader.load();
        NurseMedicinesAdministrationController nurseMedicinesAdministrationController=loader.getController();
        nurseMedicinesAdministrationController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showChangePatientBed() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/sample/Views/NurseChangePatientBed.fxml"));
        Parent root1 = (Parent) loader.load();
        NurseChangePatientBedController nurseChangePatientBedController=loader.getController();
        nurseChangePatientBedController.initValue(employee);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showChangePassword(ActionEvent actionEvent) {
        loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/ChangePassword.fxml"));
        try {
            Parent tableViewParent=loader.load();
            Scene tableViewScene=new Scene(tableViewParent);
            ChangePasswordController changePasswordController=loader.getController();
            changePasswordController.initValue(employee);
            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException, SQLException {
        employee.Logout();
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage=new Stage();
        loader=new FXMLLoader();
        Object root = loader.load(getClass().getResource("/sample/Views/Login.fxml").openStream());
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene((Parent) root, 986, 635));
        primaryStage.show();
    }


}
