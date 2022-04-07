package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.EmployeeDB;
import sample.Models.DataBaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnCheckDataBase;
    public Button btnLogin;

    public boolean flag =false;
    Employee employee = new Employee();

    /*
    * this function call first when the fxml loaded and inside it there is lambda expression to set action to the button
    * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCheckDataBase.setOnAction((actionEvent -> {
            checkDataBase();
        }));
        btnLogin.setOnAction(actionEvent -> {
            try { login(actionEvent); } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /*
     * this function attach with Check DataBase Button in GUI
     * must click on it first to check the DataBase is connected or not
     * if it connected, show an alert with text DataBase Connected
     * if not, show an alert with text DataBase not connected and that don't let the program work
     * */
    public void checkDataBase() {
        /*
         * first thing call static function that return value of Connection which it name
         * ->> getConnection()
         * from class called ->> DataBaseConnection.java you find it in folder -> Models
         * go there first
         * */
        Connection connection = DataBaseConnection.getConnection();
        if(connection!=null)
        {
            flag = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Check DataBase");
            alert.setContentText("DataBase Connected!");
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check DataBase");
            alert.setContentText("DataBase not connected");
            alert.show();
        }
    }

    /*
     * this function attach with Login Button in GUI
     * when you click on it if you don't click on check DataBase Button that return an alert tell you check DataBase First
     * if you check before then the flag is true
     * then take the text from username and password put it in staff propriety
     * and check if there is a staff that has that username and password by using the function in StaffDB.java in folder -> Models
     * If it is not found, then show alert with that
     * if it is found, then check the type of that staff
     * if the type is "Nurse" then check his shift if that is not his shift then show another alert and tell him it
     * if it, then let him in
     * and record the time he is in the system using function  .startSession() that is in LogsDB.java in folder -> Models
     * ----------------------------------------------
     * Depending on his type, the FXML Page will open
     * and move his properties with him with function name -->> .initValue(employee)
     * initValue() that is first thing will call when the FXML render and call its controller
     * so will find it in all controller
     * */
    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        if (flag){
            if (txtUsername.getText().isEmpty()||txtPassword.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();
            }
            else {
                employee.setUserName(txtUsername.getText());
                employee.setPassword(txtPassword.getText());

                employee = EmployeeDB.getLoginEmployee(employee);
                if (employee.getId()==0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("username or password is invalid\nOR\nThat is not your shift");
                    alert.show();
                }
                else{
                    employee.LogIn();
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Object root = loader.load(getClass().getResource("/sample/Views/Home.fxml").openStream());
                    primaryStage.setTitle("Home");
                    primaryStage.setScene(new Scene((Parent) root, 986, 600));

                    HomeController homeController = (HomeController) loader.getController();
                    homeController.initValue(employee);
                    primaryStage.show();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Check DataBase");
            alert.setContentText("DataBase not connected");
            alert.show();
        }
    }


}
