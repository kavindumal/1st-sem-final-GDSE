package lk.ijse.model;

import lk.ijse.controller.*;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.FrameDto;
import lk.ijse.prescriptionGeneratingCase.PrescriptionGeneratingCase;

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
}
