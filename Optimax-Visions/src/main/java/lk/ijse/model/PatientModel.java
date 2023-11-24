package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddNewAppointmentDto;
import lk.ijse.dto.PatientDto;
import lk.ijse.dto.tm.PatientTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public List<PatientTm> getAllData() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM patient";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PatientTm> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(4);
            String family = resultSet.getString(5);
            String tel = resultSet.getString(6);

            JFXButton updateBtn = new JFXButton("Update");
            updateBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Green; -fx-text-fill: white; -fx-font-size: 19px;");


            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            dtoList.add(new PatientTm(id, name, email, family,tel,updateBtn,removeBtn));
        }
        return dtoList;
    }
}