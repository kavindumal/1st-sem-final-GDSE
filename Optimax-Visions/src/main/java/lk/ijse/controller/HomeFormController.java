package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lk.ijse.dto.TransactionDto;
import lk.ijse.dto.tm.AppointmentTm;
import lk.ijse.model.AddNewAppointmentModel;
import lk.ijse.model.TransactionModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {
    @FXML
    private Pane appointmentPane;

    @FXML
    private CategoryAxis cateforyAxis;

    @FXML
    private JFXButton checkPrescriptionBtn;

    @FXML
    private Label goodStatusLbl;

    @FXML
    private AnchorPane homePane;

    @FXML
    private JFXButton newAppointmentBtn;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private StackedBarChart recentTransactionSBC;

    @FXML
    private Label timeLbl;

    public void newAppointmentBtnOnAction(ActionEvent actionEvent) {
        homePane.getChildren().clear();
        try {
            homePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/addNewAppointmentForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPrescriptionBtnOnAction(ActionEvent actionEvent) {
        homePane.getChildren().clear();
        try {
            homePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/prescriptionDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setTimeForLbl();
            }
        };
        timer.start();
        setChartData();
        setAppointmentToday();
    }

    private void setAppointmentToday() {

    }

    private void setChartData() throws SQLException {
        recentTransactionSBC.setStyle("-fx-background-color: transparent;");
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Mon");
        obList.add("Tue");
        obList.add("Wed");
        obList.add("Thu");
        obList.add("Fri");
        obList.add("Sat");
        obList.add("Sun");

        cateforyAxis.setCategories(obList);
        cateforyAxis.setLabel("Week days");

        numberAxis.setLabel("Transactions (Rupees)");
        AddNewAppointmentModel appointmentModel = new AddNewAppointmentModel();
        List<AppointmentTm> allData = appointmentModel.getAllData();

        TransactionModel model = new TransactionModel();
        List<TransactionDto> allValues = model.getAllValues();

        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Appointment");
        series1.getData().add(new XYChart.Data<>("Mon", getAppointmentDetailsForMonday(allData)));
        series1.getData().add(new XYChart.Data<>("Tue", getAppointmentDetailsForTuesday(allData)));
        series1.getData().add(new XYChart.Data<>("Wed", getAppointmentDetailsForWednesday(allData)));
        series1.getData().add(new XYChart.Data<>("Thu", getAppointmentDetailsForThursday(allData)));
        series1.getData().add(new XYChart.Data<>("Fri", getAppointmentDetailsForFriday(allData)));
        series1.getData().add(new XYChart.Data<>("Sat", getAppointmentDetailsForSaturday(allData)));
        series1.getData().add(new XYChart.Data<>("Sun", getAppointmentDetailsForSunday(allData)));

        final XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Buy Prescription");
        series2.getData().add(new XYChart.Data<>("Mon", getPrescriptionDetailsForMon(allValues)));
        series2.getData().add(new XYChart.Data<>("Tue", getPrescriptionDetailsForTue(allValues)));
        series2.getData().add(new XYChart.Data<>("Wed", getPrescriptionDetailsForWed(allValues)));
        series2.getData().add(new XYChart.Data<>("Thu", getPrescriptionDetailsForThu(allValues)));
        series2.getData().add(new XYChart.Data<>("Fri", getPrescriptionDetailsForFri(allValues)));
        series2.getData().add(new XYChart.Data<>("Sat", getPrescriptionDetailsForSat(allValues)));
        series2.getData().add(new XYChart.Data<>("Sun", getPrescriptionDetailsForSun(allValues)));

        recentTransactionSBC.getData().addAll(series1, series2);
    }

    private double getPrescriptionDetailsForSun(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForSat(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForFri(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForThu(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForWed(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForTue(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getPrescriptionDetailsForMon(List<TransactionDto> allValues) {
        double total = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getTransactionType().equals("Prescription order")) {
                if (allValues.get(i).getDate().getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                    total = total + allValues.get(i).getAmount();
                }
            }
        }
        return total;
    }

    private double getAppointmentDetailsForSunday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForSaturday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForThursday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForFriday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForWednesday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForTuesday(List<AppointmentTm> allData) {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                total = total + 500;
            }
        }
        return total;
    }

    private double getAppointmentDetailsForMonday(List<AppointmentTm> allData) throws SQLException {
        double total = 0;
        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                total = total + 500;
            }
        }
        return total;
    }


    private void setTimeForLbl() {
        java.time.LocalTime currentTime = java.time.LocalTime.now();

        String formattedTime = currentTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        String timeOfDay;
        int hour = currentTime.getHour();
        if (hour < 12) {
            timeOfDay = "Morning";
        } else if (hour >= 12 && hour < 16) {
            timeOfDay = "Afternoon";
        } else if (hour >= 16 && hour < 18){
            timeOfDay = "Evening";
        } else {
            timeOfDay = "Night";
        }

        timeLbl.setText(formattedTime);
        goodStatusLbl.setText("Good " + timeOfDay + "," + "kavindu");
    }
}
