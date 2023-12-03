package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.controller.*;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.dto.FrameDto;
import lk.ijse.dto.tm.PatientTm;
import lk.ijse.dto.tm.PrescriptionTm;
import lk.ijse.prescriptionGeneratingCase.PrescriptionGeneratingCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionModel {

    public boolean checkAppointmentId(String text) throws SQLException {
        String[][] appointmentDetails = DbConnections.getDetails("appointment", 8);
        for (int i = 0; i < appointmentDetails.length; i++) {
            if (text.equals(appointmentDetails[i][0])) {
                return true;
            }
        }
        return false;
    }

    public List<FrameDto> getGeneratedFrames() throws SQLException {
        String[][] frameDetails = DbConnections.getDetails("frame", 11);
        List<FrameDto> frameDtoList = new ArrayList<>();
        for (int i = 0; i < frameDetails.length; i++) {
            if (frameDetails[i][2].equals(PrescriptionGeneratingCase.getType(SelectGenderFormController.genderSelect))) {
                if (frameDetails[i][4].equals(PrescriptionGeneratingCase.getFaceShape(SelectFaceShapeFormContoller.faceSelect))) {
                    if (frameDetails[i][5].equals(PrescriptionGeneratingCase.getFrameShape(SelectFavoriteFormController.frameSelect))) {
                        if (frameDetails[i][6].equals(PrescriptionGeneratingCase.getColor(SelectColorFormController.colorSelect))) {
                            if (frameDetails[i][7].equals(PrescriptionGeneratingCase.getMaterial(SelectMaterialFormController.materialSelect))) {
                                frameDtoList.add(new FrameDto(frameDetails[i][0], frameDetails[i][1], frameDetails[i][2], frameDetails[i][3], frameDetails[i][4], frameDetails[i][5], frameDetails[i][6], frameDetails[i][7], Integer.parseInt(frameDetails[i][8]), Double.parseDouble(frameDetails[i][9])));
                            }
                        }
                    }
                }
            }
        }
        return frameDtoList;
    }

    public String getImage(String id) throws SQLException {
        String[][] details = DbConnections.getDetails("frame", 11);
        for (int i = 0; i < details.length; i++) {
            if (id.equals(details[i][0])) {
                return details[i][10];
            }
        }
        return null;
    }

    public boolean updateValues() throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnections.getInstance().getConnection();
            connection.setAutoCommit(false);

            LenseModel lenseModel = new LenseModel();
            FrameModel frameModel = new FrameModel();
            TransactionModel transactionModel = new TransactionModel();

            if (lenseModel.updateRightLense()) {
                if (lenseModel.updateLeftLense()) {
                    if (frameModel.updateFrameModel()) {
                        if (transactionModel.setValuesToDatabase()) {
                            if (setPrescriptionToDatabase()) {
                                if (setPrescriptionGlassToDatabase()) {
                                    connection.commit();
                                    result = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    private boolean setPrescriptionGlassToDatabase() throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.prescriptionglass (glassId, lenseId, frameId, patientId, totalPrice, time, date)\n" +
                "VALUES ('"+ generateId() +"', '"+ PresGlassSellFormController.lenseIfLeft +"', '"+ PresGlassSellFormController.frameId +"', '"+ getPatientId() +"', "+ PresGlassSellFormController.totalPrice +", '"+ LocalTime.now() +"', '"+ LocalDate.now() +"');\n" +
                "\n");
    }

    private String generateId() throws SQLException {
        String[][] prescriptionglasses = DbConnections.getDetails("prescriptionglass", 7);
        if (prescriptionglasses.length == 0) {
            return "G0001";
        }
        String lastPrescription = prescriptionglasses[prescriptionglasses.length - 1][0];

        int numericPart = Integer.parseInt(lastPrescription.replaceFirst("^G0*", ""));
        int incrementedNumericPart = numericPart + 1;
        return String.format("G%04d", incrementedNumericPart);
    }


    private boolean setPrescriptionToDatabase() throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.prescription (patientId, sphereRight, sphereLeft, cylRight, cylLeft, axisRight, axisLeft,\n" +
                "                                     addRight, addLeft, pdDistance)\n" +
                "VALUES ('"+ getPatientId() +"', "+ PrescriptionDetailsFormController.sphereRight +", "+ PrescriptionDetailsFormController.sphereLeft +", "+ PrescriptionDetailsFormController.cylRight +", "+ PrescriptionDetailsFormController.cylLeft +", "+ PrescriptionDetailsFormController.axisRight +", "+ PrescriptionDetailsFormController.axisLeft +", "+ PrescriptionDetailsFormController.addRight +", "+ PrescriptionDetailsFormController.addLeft +", "+ PrescriptionDetailsFormController.pd +");\n" +
                "\n");
    }

    private String getPatientId() throws SQLException {
        String[][] details = DbConnections.getDetails("appointment", 8);
        for (int i = 0; i < details.length; i++) {
            if (PrescriptionDetailsFormController.appointmentId.equals(details[i][0])) {
                patientId = details[i][6];
                return details[i][6];
            }
        }
        return null;
    }

    public static String patientId;

    public List<PrescriptionTm> getAllValues() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM prescriptionglass";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PrescriptionTm> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String Gid = resultSet.getString(1);
            String LId = resultSet.getString(2);
            String FId = resultSet.getString(3);
            String PId = (resultSet.getString(4));
            Double totalPrice = (resultSet.getDouble(5));
            LocalTime time = resultSet.getTime(6).toLocalTime();
            LocalDate date = resultSet.getDate(7).toLocalDate();

            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            dtoList.add(new PrescriptionTm(Gid, getFrameType(FId), PId, getPName(PId), time, date, removeBtn));
        }
        return dtoList;
    }

    private String  getPName(String pId) throws SQLException {
        PatientModel patientModel = new PatientModel();
        List<PatientTm> allData = patientModel.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getId().equals(pId)) {
                return allData.get(i).getName();
            }
        }
        return null;
    }

    private String getFrameType(String fId) throws SQLException {
        FrameModel frameModel = new FrameModel();
        List<FrameDetailsDto> allValues = frameModel.getAllValues();
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getId().equals(fId)) {
                return allValues.get(i).getType();
            }
        }
        return null;
    }
}
