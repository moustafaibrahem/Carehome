package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    * this function open first FXML file (Login.FXML) that is in folder -> Views
    * Login.FXML attach with controller LoginController.java that is in folder -> Controllers
    * */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 986, 635));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
