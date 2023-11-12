package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddNewAppointmentDto;
import lk.ijse.dto.PatientDto;

import java.sql.SQLException;

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

    public String getNewPatientId() throws SQLException {
        String[][] details = DbConnections.getDetails("patient", 6);
        String lastPatientId = details[details.length - 1][0];

        int numericPart = Integer.parseInt(lastPatientId.replaceFirst("^P0*", ""));
        int incrementedNumericPart = numericPart + 1;

        return String.format("P%04d", incrementedNumericPart);
    }

    public boolean setValuestoDatabase(AddNewAppointmentDto appointmentDto, PatientDto patientDto) throws SQLException {
        String[][] doctorData = DbConnections.getDetails("doctor", 7);

        String doctorId = null;
        for (int i = 0; i < doctorData.length; i++) {
            if ((" Dr. "+doctorData[i][1]).equals(appointmentDto.getDoctor())) {
                doctorId = doctorData[i][0];
            }
        }
        return DbConnections.setDetails("INSERT INTO visioncare.appointment (appoitmentId, time, date, problem, docterId, prescription, patientId)\n" +
                "VALUES ('" + appointmentDto.getId() + "', '" + appointmentDto.getTime() + "', '" + appointmentDto.getDate() + "', '" + appointmentDto.getProblem() + "', '" + doctorId +"', '" + appointmentDto.getPrescription() + "', '" + patientDto.getPatientId() + "');\n" +
                "\n");
    }

    public boolean setPatientDetailsToDatabase(PatientDto patientDto) throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.patient (patientId, fullName, address, email, familyName, telNo)\n" +
                "VALUES ('" + patientDto.getPatientId() + "', '" + patientDto.getFullname() + "', '" + patientDto.getAddress() + "', '" + patientDto.getEmail() + "', '" + patientDto.getFamilyname() + "', "+ patientDto.getTelNo() +");\n" +
                "\n");
    }

    public boolean setAppointmentDetailstoDatabase(AddNewAppointmentDto appointmentDto, PatientDto patientDto) throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.appointment (appoitmentId, time, date, problem, docterId, prescription, patientId)\n" +
                "VALUES ('" + appointmentDto.getId() + "', '" + appointmentDto.getTime() + "', '" + appointmentDto.getDate() + "', '" + appointmentDto.getProblem() + "', '" + appointmentDto.getDoctor() +"', '" + appointmentDto.getPrescription() + "', '" + patientDto.getPatientId() + "');\n" +
                "\n");
    }
}
