package lk.ijse.prescriptionGeneratingCase;

import lk.ijse.controller.PrescriptionDetailsFormController;

public class PrescriptionGenerator {
    public static void checkSuitableLenseForRightEyeGlass() {
        if (PrescriptionDetailsFormController.sphereRight > 0) {
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
