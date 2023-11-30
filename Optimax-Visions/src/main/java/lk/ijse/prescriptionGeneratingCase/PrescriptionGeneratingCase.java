package lk.ijse.prescriptionGeneratingCase;

public class PrescriptionGeneratingCase {
    public static String getType(int number) {
        if (number == 1) return "male";
        else return "female";
    }

    public static String getWearPlace(int number) {
        if (number == 1) return "outdoor";
        else if (number == 2) return "indoor";
        else  return "both";
    }

    public static String getFaceShape(int number) {
        if (number == 1) return "triangle";
        else if (number == 2) return "oval";
        else if (number == 3) return "square";
        else if (number == 4) return "round";
        else if (number == 5) return "heart";
        else if (number == 6) return "diamond";
        else return "unsure";
    }

    public static String getFrameShape(int number) {
        if (number == 1) return "browline";
        else if (number == 2) return "round";
        else if (number == 3) return "square";
        else if (number == 4) return "cat";
        else if (number == 5) return "aviator";
        else return "special";
    }

    public static String getColor(int number) {
        if (number == 1) return "black";
        else if (number == 2) return "tortoise";
        else if (number == 3) return "pattern";
        else if (number == 4) return "neutral";
        else if (number == 5) return "colorful";
        else return "translucent";
    }

    public static String getMaterial(int number) {
        if (number == 1) return "metal";
        else if (number == 2) return "plastic";
        else if (number == 3) return "mixed";
        else return "eco";
    }
}
