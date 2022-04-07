package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.*;
import sample.Models.BasicModel.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManagerStaffController implements Initializable {

    public ComboBox<String> cmbType;
    public ComboBox<String> cmbShift;
    public Label lblStartTime;
    public Label lblEndTime;
    public TableView<Employee> tvStaff;
    public TableColumn<Employee,Long> colID;
    public TableColumn<Employee,String> colName;
    public TableColumn<Employee,String> colPhone;
    public TableColumn<Employee,String> colUsername;
    public TableColumn<Employee,String>colPassword;
    public TableColumn<Employee,String>colType;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtUsername;
    public TextField txtPassword;
    public Button btnAddStaff;
    public Button btnUpdate;
    public Button btnDelete;

    boolean selectedType=false;
    boolean selectedShift=false;


    ObservableList<Shifts>  shifts;
    final ObservableList typesOption= FXCollections.observableArrayList();

    Manager manager=null;
    Employee doAction=null;

    Employee employee = null ;
    Employee employeeUpdate=null;

    Nurse nurse=null;

    /*
     * the manager properties will assign to the staff her to record what he does
     * */

    public void initValue(Employee employee){
        this.doAction=employee;
    }

    /*
     * this function call another function called GetEmployeeList() from EmployeeDB class in folder -> Models
     * the GetEmployeeList() return list of Employee
     * and put it all in table view
     * */
    public void showEmployeeList() throws SQLException {

        ObservableList<Employee> EmployeeList = EmployeeDB.GetEmployeeList();

        colID.setCellValueFactory(new PropertyValueFactory<Employee,Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Employee,String>("phone"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Employee,String>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Employee,String>("password"));
        colType.setCellValueFactory(new PropertyValueFactory<Employee,String>("type"));

        tvStaff.setItems(EmployeeList);
    }

    /*
     * here when the page render the first thing call is this function
     * and then this function call another function like  showEmployeeList()
     * and GetEmployeeList() from EmployeeDB class in folder -> Models
     * that return the all shifts id in database in list
     * then put it in combo box -> cmbShift
     * and getShiftsList() from ShiftsDB class in folder -> Models
     * then return all object in list of shifts
     *
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            manager=new Manager();
            employee=new Employee();
            employeeUpdate=new Employee();
            nurse=new Nurse();

            showEmployeeList();
            cmbShift.setItems(FXCollections.observableArrayList(ShiftsDB.getShiftsIdList()));
            shifts = ShiftsDB.getShiftsList();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        typesOption.add("Doctor");
        typesOption.add("Nurse");
        cmbType.setItems(FXCollections.observableArrayList(typesOption));

        btnAddStaff.setOnAction(actionEvent -> {
            try { addStaff(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnUpdate.setOnAction(actionEvent -> {
            try { updateStaff(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnDelete.setOnAction(actionEvent -> {
            try { deleteStaff(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    /*
    * when he choose the type put it in staff type
    *
    * */

    public void getType() {
        selectedType=true;
        employee.setType(cmbType.getValue());
    }

    /*
    * when he choice the shift from the combo box then take that id and select from shifts list
    * the specific shift and show the start time and the end time of this shift
    * in labels
    * */

    public void ShowShiftTime() {
        selectedShift=true;
        lblStartTime.setText(String.valueOf(shifts.get(cmbShift.getSelectionModel().getSelectedIndex()).getStartTime()));
        lblEndTime.setText(String.valueOf(shifts.get(cmbShift.getSelectionModel().getSelectedIndex()).getEndTime()));
        nurse.setShiftId(Long.parseLong(cmbShift.getValue()));
    }

    /*
     * first check if all text field is not empty
     * if it is ,then show alert
     * if all is ok then call function AddDoctor() or AddNurse() in Manager class in folder -> Models
     * and then call showEmployeeList() to refresh the table view
     * */

    public void addStaff() throws SQLException {
        employee.setName(txtName.getText());
        employee.setPhone(txtPhone.getText());
        employee.setUserName(txtUsername.getText());
        employee.setPassword(txtPassword.getText());
        boolean mistak=check();
        if (employee.getName().isEmpty()||employee.getPhone().isEmpty()||employee.getUserName().isEmpty()||employee.getPassword().isEmpty()||selectedType==false|| !mistak ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            if (employee.getType().equals("Doctor")) {
                Doctor doctor=new Doctor(employee);
                manager.AddDoctor(doctor);
            }
            else if (employee.getType().equals("Nurse")){
                nurse=new Nurse(employee,nurse.getShiftId());
                manager.AddNurse(nurse);
            }
            showEmployeeList();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been Added");
            alert.showAndWait();
        }

    }

    /*
     * first check if there is any of this fields is empty if it is then show alert
     * else, we check if the data are the same if it is then show alert say "there is no updated data"
     * else, make the update using the function EditDoctor() or EditNurse() in Manager class in folder -> Models
     * and then call showEmployeeList() to refresh the table view
     * */

    public void updateStaff() throws SQLException {
        employee.setId(employeeUpdate.getId());
        employee.setName(txtName.getText());
        employee.setPhone(txtPhone.getText());
        employee.setUserName(txtUsername.getText());
        employee.setPassword(txtPassword.getText());
        boolean mistak=check();
        if (employee.getName().isEmpty()||employee.getPhone().isEmpty()||employee.getUserName().isEmpty()||employee.getPassword().isEmpty()||selectedType==false|| !mistak ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA \n Please make sure you choice 1 || 2 shift for Nurse \\n&&\\n 3 shift for Doctor");
            alert.showAndWait();
        }
        else if (employeeUpdate.equals(employee)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("There is no updated data");
            alert.showAndWait();
        }
        else{
            if (employee.getType().equals("Doctor")) {
                Doctor doctor=new Doctor(employee);
                manager.EditDoctor(doctor);
            }
            else if (employee.getType().equals("Nurse")){
                nurse=new Nurse(employee,nurse.getShiftId());
                manager.EditNurse(nurse);
            }
            showEmployeeList();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been updated");
            alert.showAndWait();
        }
    }

    /*
     * the manager when he click on deleted button
     * then we don't delete the row we put the endeffectiveDate with the sysdate and don't show his information
     * that has done by using the DeleteDoctor() or DeleteNurse() in Manager class in folder -> Models
     * and then call showEmployeeList() to refresh the table view
     * */

    public void deleteStaff() throws SQLException {
        employee.setId(employeeUpdate.getId());
        employee.setName(txtName.getText());
        employee.setPhone(txtPhone.getText());
        employee.setUserName(txtUsername.getText());
        employee.setPassword(txtPassword.getText());
        if (employee.getName().isEmpty()||employee.getPhone().isEmpty()||employee.getUserName().isEmpty()||employee.getPassword().isEmpty()||selectedType==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
            if (employee.getType().equals("Doctor")) {
                Doctor doctor=new Doctor(employee);
                manager.DeleteDoctor(doctor);
            }
            else if (employee.getType().equals("Nurse")){
                nurse=new Nurse(employee,nurse.getShiftId());
                manager.DeleteNurse(nurse);
            }
            showEmployeeList();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been Deactivated");
            alert.showAndWait();
        }
    }

    /*
     * this function using when he selected row from table view
     * and then show this information in the text field and combo box
     * */

    public void getSelectedRow() {
        Employee employee = tvStaff.getSelectionModel().getSelectedItem();
        txtName.setText(employee.getName());
        txtPhone.setText(employee.getPhone());
        txtUsername.setText(employee.getUserName());
        txtPassword.setText(employee.getPassword());
        cmbType.setValue(employee.getType());
        lblEndTime.setText("");
        lblStartTime.setText("");

        selectedType=true;
        selectedShift=true;

        employeeUpdate.setId(employee.getId());
        employeeUpdate.setName(employee.getName());
        employeeUpdate.setPhone(employee.getPhone());
        employeeUpdate.setUserName(employee.getUserName());
        employeeUpdate.setPassword(employee.getPassword());
        employeeUpdate.setType(employee.getType());
    }

    /*
     * this function used to clear the text field and combo box
     * */
    private void clear(){
        txtName.clear();
        txtPhone.clear();
        txtUsername.clear();
        txtPassword.clear();
        selectedType=false;
        selectedShift=false;
        lblStartTime.setText("");
        lblEndTime.setText("");
    }

    /*
    * this function to check if he select nurse and doesn't select shift
    * */

    private boolean check(){
        if (employee.getType().equals("Nurse") && selectedShift==false ){
           return false;
        }
        else{
            return true;
        }
    }


}
