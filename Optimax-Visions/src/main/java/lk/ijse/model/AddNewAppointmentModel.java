package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddNewAppointmentDto;

import java.sql.SQLException;

public class AddNewAppointmentModel {
    public String[][] getEqualDateAppoitments(String replace) throws SQLException {
        String[][] getAppoitments = DbConnections.getDetails("appointment", 7, "SELECT *\n" +
                "FROM appointment\n" +
                "WHERE date = '"+ replace +"';", replace);
        return getAppoitments;
    }

    public String findNextAppoitmentId() throws SQLException {
        String[][] getLastId = DbConnections.getDetails("appointment", 7);

        String lastPatientId = getLastId[getLastId.length - 1][0];
        int numericPart = Integer.parseInt(lastPatientId.replaceFirst("^A0*", ""));
        int incrementedNumericPart = numericPart;
        return String.format("A%04d", incrementedNumericPart);
    }
}
