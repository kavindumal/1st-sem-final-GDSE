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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
import java.util.*;

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
        setExecutiveSummery();
    }

    private void setExecutiveSummery() throws SQLException {
        Pane pane1 = new Pane();
        pane1.setStyle("-fx-background-color: rgba(255,255,255,0.51); -fx-background-radius: 30; ");
        pane1.setPrefWidth(252);
        pane1.setPrefHeight(256);
        pane1.setLayoutX(110);
        pane1.setLayoutY(604);
        homePane.getChildren().add(pane1);

        Pane pane2 = new Pane();
        pane2.setStyle("-fx-background-color: rgba(255,255,255,0.51); -fx-background-radius: 30; ");
        pane2.setPrefWidth(252);
        pane2.setPrefHeight(256);
        pane2.setLayoutX(390);
        pane2.setLayoutY(604);
        homePane.getChildren().add(pane2);

        TransactionModel model = new TransactionModel();
        List<TransactionDto> allValues = model.getAllValues();

        double todayTotal = 0;
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getDate().equals(LocalDate.now())) {
                todayTotal = todayTotal + allValues.get(i).getAmount();
            }
        }
        Label label1 = new Label();
        label1.setText("Yesterday income");
        label1.setLayoutX(132);
        label1.setLayoutY(668);
        label1.setStyle("-fx-font-size: 26px");

        homePane.getChildren().add(label1);

        Label label2 = new Label();
        label2.setText("Rs. " + todayTotal);
        label2.setLayoutX(140);
        label2.setLayoutY(745);
        label2.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-alignment: center");

        homePane.getChildren().add(label2);
    }

    private void setAppointmentToday() throws SQLException {
        AddNewAppointmentModel model =new AddNewAppointmentModel();
        List<AppointmentTm> allData = model.getAllData();

        List<AppointmentTm> getTodayData = new ArrayList<>();
        List<Double> times = new ArrayList<>();

        for (int i = 0; i < allData.size(); i++) {
            if (LocalDate.parse(allData.get(i).getDate()).equals(LocalDate.now())) {
                getTodayData.add(allData.get(i));
                times.add(Double.parseDouble(allData.get(i).getTime()));
            }
        }
        Collections.sort(times);

        int count1 = 154;
        int count2 = 167;
        int count3 = 177;

        for (int i = 0; i < times.size(); i++) {
            Pane pane1 = new Pane();
            pane1.setStyle("-fx-background-color: rgba(255,255,255,0.51); -fx-background-radius: 30; ");
            pane1.setPrefWidth(200);
            pane1.setPrefHeight(80);
            pane1.setLayoutX(1391);
            pane1.setLayoutY(count1);
            homePane.getChildren().add(pane1);

            ImageView imageView = new ImageView();
            Pane pane2 = new Pane();
            imageView.setImage(new Image("/img/icons/pulse.gif"));

            Label label = new Label();
            for (int j = 0; j < getTodayData.size(); j++) {
                if (Double.parseDouble(getTodayData.get(j).getTime()) == times.get(i)) {
                    if (times.get(i) >= 8 && times.get(i) < 12.00) {
                        label.setText(getTodayData.get(i).getTime() + " A.M.");
                    } else {
                        label.setText(getTodayData.get(i).getTime() + " P.M.");
                    }
                }
            }


            label.setStyle("-fx-font-size: 18px;");
            pane2.setStyle("-fx-background-radius: 30;-fx-background-color: blue");

            pane2.setPrefWidth(67);
            pane2.setPrefHeight(79);
            pane2.setLayoutX(1391);
            pane2.setLayoutY(count1);
            homePane.getChildren().add(pane2);

            imageView.setFitWidth(45);
            imageView.setFitHeight(45);
            imageView.setLayoutX(1405);
            imageView.setLayoutY(count2);
            homePane.getChildren().add(imageView);;;

            label.setLayoutX(1475);
            label.setLayoutY(count3);
            homePane.getChildren().add(label);

            count1 = count1 + 106;
            count2 = count2 + 106;
            count3 = count3 + 106;
        }
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
