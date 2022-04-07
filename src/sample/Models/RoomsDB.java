package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
    * this function return the count of all used bed in this room
    * to know if its free or there is else
    * */

    static public long getUsedBedNumberInRoom(long roomId) throws SQLException {
        query = "select COUNT(*) from patients pa where pa.bed_id in (SELECT be.bed_ID FROM beds be where be.room_id='"+roomId+"') AND pa.discharged is NULL;";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            return resultSet.getLong(1);
        }
        return 0;

    }

    /*
    * this function return only the id of room using the bed id
    * */

    static public long getRoomId(long bedId) throws SQLException {
        query = "SELECT be.room_id FROM beds be WHERE be.bed_ID='"+bedId+"';";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getLong(1);
        }
        return 0;
    }

    /*
    * this function return the list of rooms under this parameters
    * */

    static public ObservableList getRoomsIdList(String wardId,String gender,boolean checkBoxSelected) throws SQLException {
        ObservableList roomOption = FXCollections.observableArrayList();
        if (checkBoxSelected){
            query="SELECT * FROM `rooms` WHERE `ward_id`='"+wardId+"'and `type_id` = 'free' ;";
        }
        else{
            if (gender.equals("male")){
                gender="female";
                query="SELECT * FROM `rooms` ro WHERE `ward_id`='"+wardId+"' and `type_id`<> '"+gender+"' and room_ID   not in (select be.room_ID from beds be where be.room_id=ro.room_ID and be.bed_ID in (select bed_id from patients ps where ps.bed_id =be.bed_ID and isIsolted=1 and discharged is null )); \n";
            }
            else {
                gender="male";
                query="SELECT * FROM `rooms` ro WHERE `ward_id`='"+wardId+"' and `type_id`<> '"+gender+"' and room_ID   not in (select be.room_ID from beds be where be.room_id=ro.room_ID and be.bed_ID in (select bed_id from patients ps where ps.bed_id =be.bed_ID and isIsolted=1 and discharged is null )); \n";
            }
        }
        connection = DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next())
        {
            roomOption.add(String.valueOf(resultSet.getLong("room_ID")));
        }

        return  roomOption;
    }

    /*
     * this function update the room type using id and set the type is free
     * */

    static public void updateRoomType(String roomId) throws SQLException {
        updateRoomType("free", roomId);
    }

    /*
     * this function update the room type using id and set the type under the patient gender
     * */

    static public void updateRoomType(String gender, String roomId) throws SQLException {
        query="UPDATE `rooms` SET `type_id` = '"+gender+"' WHERE `rooms`.`room_ID` = '"+roomId+"';";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
    * this function return the all information of that room using thr bed id that is in this room
    * */

    static public Rooms getRoom(String roomId) throws SQLException {
        Rooms rooms = new Rooms();
        query="SELECT * FROM `rooms` WHERE `room_ID` = '"+roomId+"'";
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery(query);
        while (resultSet.next()){
            rooms = new Rooms(
                    resultSet.getLong("room_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("type_id"),
                    resultSet.getLong("ward_id")
            );
        }
        return rooms;
    }

}
