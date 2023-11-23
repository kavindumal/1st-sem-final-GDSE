package lk.ijse.model;

import lk.ijse.db.DbConnections;

import java.sql.SQLException;

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
}
