package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddEmployeeDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.FrameDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeModel {
    public List<EmployeeDto> getAllValues() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<EmployeeDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String jobTitle = resultSet.getString(3);
            int age = findAge(resultSet.getDate(4));
            int telNo = resultSet.getInt(5);

            JFXButton updateBtn = new JFXButton("Update");
            updateBtn.setStyle("-fx-background-radius: 30; -fx-background-color: green; -fx-text-fill: white; -fx-font-size: 19px;");

            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            JFXButton viewBtn = new JFXButton("View paysheet");
            viewBtn.setStyle("-fx-background-radius: 30; -fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 19px;");


            dtoList.add(new EmployeeDto(id,name, jobTitle, age,telNo,updateBtn,removeBtn,viewBtn));
        }
        return dtoList;
    }

    private int findAge(Date date) {
        LocalDate birthDate = new java.sql.Date(date.getTime()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public boolean setEmployeeToDatabase(AddEmployeeDto dto) throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.employee (nicNumber, name, jobTitle, dob, telNo, basicSalary, profilePhoto)\n" +
                "VALUES ('"+ dto.getNicNumber() +"', '"+ dto.getName() +"', '"+ dto.getJobTitle() +"', '"+ dto.getDob() +"', "+ dto.getTelNo() +", "+ dto.getBasicSalary() +", '"+ dto.getProfileLink() +"');\n" +
                "\n");
    }

    public boolean deleteEmployeeFromDatabase(String id) throws SQLException {
        return DbConnections.setDetails("DELETE\n" +
                "FROM visioncare.employee\n" +
                "WHERE nicNumber LIKE '"+ id +"' ESCAPE '#';\n" +
                "\n");
    }

    public String[][] getDataFromEmployee() throws SQLException {
        return DbConnections.getDetails("employee",7);
    }
}