package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.PatientDto;

import java.sql.SQLException;
import java.util.List;

public class PatientModel {
    public PatientDto getPatientDetails(PatientDto dto) throws SQLException {
        String[][] details = DbConnections.getDetails("patient", 6);
        PatientDto dtoNew = null;
        for (int i = 0; i < details.length; i++) {
            if ((details[i][0].equals(dto.getPatientId()))) {
                dtoNew = new PatientDto(details[i][0], details[i][1], details[i][2], details[i][3], details[i][4], details[i][5]);
            }
        }
        return dtoNew;
    }

    public boolean checkAvailable(PatientDto dto) throws SQLException {
        String[][] details = DbConnections.getDetails("patient", 6);
        for (int i = 0; i < details.length; i++) {
            if ((details[i][0].equals(dto.getPatientId()))) {
                return true;
            }
        }
        return false;
    }
}
