package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.BasicModel.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {

    public TextField txtOldPassword;
    public TextField txtNewPassword;
    public Button btnConfirm;
    public Button btnBack;


    Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnConfirm.setOnAction(actionEvent -> {
            try { ConfirmChangePassword(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnBack.setOnAction(actionEvent -> {
            try { goHome(actionEvent); } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /*
     * the staff properties will assign to the staff her to record what he does
     * because the ChangePassword.fxml
     * */

    public void initValue(Employee employee){
        this.employee=employee;
        txtOldPassword.setText(employee.getPassword());
    }

    /*
    * this function take the text from text field if it empty show alert
    * else, using the function updateStaff() in StaffDB class in folder -> Models
    * */

    public void ConfirmChangePassword() throws SQLException {
        if (txtOldPassword.getText().isEmpty()||txtNewPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
            employee.setPassword(txtNewPassword.getText());
            employee.ChangePassword();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been updated");
            alert.showAndWait();
        }
    }

    /*
     * this function used to clear the text field
     * */

    private void clear(){
        txtOldPassword.clear();
        txtNewPassword.clear();
    }

    /*
     * this function used to return the previous form
     * */

    public void goHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Views/Home.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        HomeController homeController = loader.getController();
        homeController.initValue(employee);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(tableViewScene);
        stage.show();
    }
}
