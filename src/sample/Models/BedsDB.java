package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BedsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
     * this function return the list of beds under the room id
     * */

    static public ObservableList getBedsIdList(String roomID) throws SQLException {
        ObservableList bedOption = FXCollections.observableArrayList();
        query="SELECT b.bed_ID FROM `beds` b WHERE `room_id` = '"+roomID+"' And Not EXISTS (SELECT bed_id FROM patients p where p.bed_id=b.bed_ID and p.discharged is null) ;";
        connection = DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next())
        {
            bedOption.add(String.valueOf(resultSet.getLong("bed_ID")));
        }
       return bedOption;
    }

}
