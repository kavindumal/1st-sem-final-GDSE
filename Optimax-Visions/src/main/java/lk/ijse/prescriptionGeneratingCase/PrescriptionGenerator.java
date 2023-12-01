package lk.ijse.prescriptionGeneratingCase;

import lk.ijse.controller.PrescriptionDetailsFormController;

public class PrescriptionGenerator {
    public static String lenseTypeForRightEye;
    public static String detailsForRightEye;
    public static String lenseTypeForLeftEye;
    public static String detailsForLeftEye;

    public static void checkSuitableLenseForRightEyeGlass() {
        if (PrescriptionDetailsFormController.sphereRight > 0) {
            if (PrescriptionDetailsFormController.cylRight == 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes. ";
                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far . A Farsightedness lens is suitable for eyes. Correct presbyopia added because the natural farsightedness that happens with age";
                } else if (PrescriptionDetailsFormController.addRight > 4) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes. Correct presbyopia added because the natural farsightedness that happens with age and Do eye surgery quickly!!!";
                }
            } else if (PrescriptionDetailsFormController.cylRight > 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes and He has astigmatism.Astigmatism is caused by rotational asymmetry in the eye's refractive power, and it often occurs at birth and can change or develop later in life.";
                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes and He has astigmatism.Astigmatism is caused by rotational asymmetry in the eye's refractive power, and it often occurs at birth and can change or develop later in life. Correct presbyopia added because the natural farsightedness that happens with age";
                } else if (PrescriptionDetailsFormController.addRight > 4) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes and He has astigmatism.Astigmatism is caused by rotational asymmetry in the eye's refractive power, and it often occurs at birth and can change or develop later in life. Correct presbyopia added because the natural farsightedness that happens with age and Do eye surgery quickly!!!";
                }
            } else if (PrescriptionDetailsFormController.cylRight < 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {
                    lenseTypeForRightEye = "Farsightedness";
                    detailsForRightEye = "He or she doesn't show far. A Farsightedness lens is suitable for eyes and He has astigmatism.Astigmatism is caused by rotational asymmetry in the eye's refractive power, and it often occurs at birth and can change or develop later in life. Correct presbyopia added because the natural farsightedness that happens with age and Do eye surgery quickly!!!";
                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {

                } else if (PrescriptionDetailsFormController.addRight > 4) {

                }
            }
        } else if (PrescriptionDetailsFormController.sphereRight < 0) {
            if (PrescriptionDetailsFormController.cylRight == 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {

                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {

                } else if (PrescriptionDetailsFormController.addRight > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylRight > 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {

                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {

                } else if (PrescriptionDetailsFormController.addRight > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylRight < 0) {
                if (PrescriptionDetailsFormController.addRight == 0) {

                } else if (PrescriptionDetailsFormController.addRight > 0 && PrescriptionDetailsFormController.addRight <= 4) {

                } else if (PrescriptionDetailsFormController.addRight > 4) {

                }
            }
        }
    }

    public static void checkSuitableLenseForLeftEyeGlass() {
        if (PrescriptionDetailsFormController.sphereLeft > 0) {
            if (PrescriptionDetailsFormController.cylLeft == 0) {
                if (PrescriptionDetailsFormController.addLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylLeft > 0) {
                if (PrescriptionDetailsFormController.cylLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylLeft < 0) {
                if (PrescriptionDetailsFormController.addLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            }
        } else if (PrescriptionDetailsFormController.sphereLeft < 0) {
            if (PrescriptionDetailsFormController.cylLeft == 0) {
                if (PrescriptionDetailsFormController.addLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylLeft > 0) {
                if (PrescriptionDetailsFormController.addLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            } else if (PrescriptionDetailsFormController.cylLeft < 0) {
                if (PrescriptionDetailsFormController.addLeft == 0) {

                } else if (PrescriptionDetailsFormController.addLeft > 0 && PrescriptionDetailsFormController.addLeft <= 4) {

                } else if (PrescriptionDetailsFormController.addLeft > 4) {

                }
            }
        }
    }
}
