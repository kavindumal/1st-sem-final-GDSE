package lk.ijse.model;

import lk.ijse.controller.*;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.FrameDto;
import lk.ijse.dto.LenseDto;
import lk.ijse.dto.tm.PrescriptionTm;
import lk.ijse.prescriptionGeneratingCase.PrescriptionGeneratingCase;

import java.sql.Connection;
import java.sql.SQLException;
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
                                connection.commit();
                                result = true;
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
        LenseModel lenseModel = new LenseModel();
        List<LenseDto> lenseDetails = lenseModel.getAllValues();

        return  null;
    }
}
