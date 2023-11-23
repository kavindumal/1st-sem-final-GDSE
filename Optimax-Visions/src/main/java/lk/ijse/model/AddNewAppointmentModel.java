package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.AppointmentTDto;
import lk.ijse.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewAppointmentModel {
    public String[][] getEqualDateAppoitments(String replace) throws SQLException {
        return DbConnections.getDetails("appointment", 8, "SELECT *\n" +
                "FROM appointment\n" +
                "WHERE date = '"+ replace +"';", replace);

    }

    public String findNextAppoitmentId() throws SQLException {
        String[][] getLastId = DbConnections.getDetails("appointment", 8);
        if (getLastId == null || getLastId.length == 0) {
            return "A0001";
        } else {
            String lastPatientId = getLastId[getLastId.length - 1][0];
            int numericPart = Integer.parseInt(lastPatientId.replaceFirst("^A0*", ""));
            int incrementedNumericPart = numericPart + 1;
            return String.format("A%04d", incrementedNumericPart);
        }
    }

    public String[][] getDoctorsData() throws SQLException {
        return DbConnections.getDetails("doctor", 7);
    }

    public String[][] getResentAppointments() throws SQLException {
        return DbConnections.getDetails("appointment", 8);
    }

    public List<AppointmentTDto> getAllData() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM appointment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppointmentTDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String time = resultSet.getString(2);
            String date = resultSet.getString(3);
            String problem = resultSet.getString(4);
            String doctorName = getDocterName(resultSet.getString(5));
            String patientName = getPatientName(resultSet.getString(7));

            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            dtoList.add(new AppointmentTDto(id, time, date, problem,doctorName,patientName,removeBtn));
        }
        return dtoList;
    }

    private String getPatientName(String string) throws SQLException {
        String[][] details = DbConnections.getDetails("patient", 6);
        for (int i = 0; i < details.length; i++) {
            if (details[i][0].equals(string)) {
                return details[i][1];
            }
        }
        return "";
    }

    private String getDocterName(String string) throws SQLException {
        String[][] details = DbConnections.getDetails("doctor", 7);
        for (int i = 0; i < details.length; i++) {
            if (details[i][0].equals(string)) {
                return details[i][1];
            }
        }
        return "any";
    }
}