package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddNewAppointmentDto;
import lk.ijse.dto.PatientDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public boolean setPatientPaymentAppointment(AddNewAppointmentDto appointmentDto, PatientDto patientDto) throws SQLException {
        Connection connection = null;
        boolean result = false;
        try {
            connection = DbConnections.getInstance().getConnection();
            connection.setAutoCommit(false);
            System.out.println("yes");
            if (DbConnections.setDetails("INSERT INTO visioncare.patient (patientId, fullName, address, email, familyName, telNo)\n" +
                    "VALUES ('" + patientDto.getPatientId() + "', '" + patientDto.getFullname() + "', '" + patientDto.getAddress() + "', '" + patientDto.getEmail() + "', '" + patientDto.getFamilyname() + "', "+ patientDto.getTelNo() +");\n" +
                    "\n")) {
                System.out.println("patient");
                if (DbConnections.setDetails("INSERT INTO visioncare.payment (description, paymentType, time, date, amount)\n" +
                        "VALUES ('Pay for appointment', '"+ "cash" +"', '"+ LocalTime.now() +"', '"+ LocalDate.now() +"', 500);\n" +
                        "\n")) {
                    System.out.println("paymeent");
                    String[][] payments = DbConnections.getDetails("payment", 6);
                    if (DbConnections.setDetails("INSERT INTO visioncare.appointment (appoitmentId, time, date, problem, docterId, prescription, patientId, paymentId)\n" +
                            "VALUES ('" + appointmentDto.getId() + "', '" + appointmentDto.getTime() + "', '" + appointmentDto.getDate() + "', '" + appointmentDto.getProblem() + "', '" + appointmentDto.getDoctor() +"', '" + appointmentDto.getPrescription() + "', '" + patientDto.getPatientId() + "', " + payments[payments.length-1][0] + ");\n" +
                            "\n")) result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            System.out.println("auto true");
        }
        return result;
    }

    public boolean setPaymentAppointmentDetails(AddNewAppointmentDto appointmentDto, PatientDto patientDto) throws SQLException {
        Connection connection = null;
        boolean result = false;

        try {
            connection = DbConnections.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (DbConnections.setDetails("INSERT INTO visioncare.payment (description, paymentType, time, date, amount)\n" +
                        "VALUES ('Pay for appointment', '"+ "cash" +"', '"+ LocalTime.now() +"', '"+ LocalDate.now() +"', 500);\n" +
                        "\n")) {
                String[][] payments = DbConnections.getDetails("payment", 6);
                if (DbConnections.setDetails("INSERT INTO visioncare.appointment (appoitmentId, time, date, problem, docterId, prescription, patientId, paymentId)\n" +
                        "VALUES ('" + appointmentDto.getId() + "', '" + appointmentDto.getTime() + "', '" + appointmentDto.getDate() + "', '" + appointmentDto.getProblem() + "', '" + appointmentDto.getDoctor() +"', '" + appointmentDto.getPrescription() + "', '" + patientDto.getPatientId() + "', " + payments[payments.length-1][0] + ");\n" +
                        "\n")) result = true;
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}