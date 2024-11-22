package com.example.data_collection;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.beans.EventHandler;
//import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Create the main layout
        BorderPane mainLayout = new BorderPane();

        // Top banner
        Label bannerLabel = new Label("Data Collection SeTup");
        //bannerLabel.setStyle("-fx-font-size: 54px; -fx-font-weight: BOLD;");
        bannerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        bannerLabel.setPadding(new Insets(20));
        bannerLabel.setMaxWidth(Double.MAX_VALUE);
        bannerLabel.setStyle("-fx-alignment: center; -fx-background-color: DARKCYAN;");
        mainLayout.setTop(bannerLabel);

        // Form layout
        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(20));
        formLayout.setHgap(10);
        formLayout.setVgap(10);

        // Add fields
        formLayout.add(new Label("Name:"), 0, 0);
        TextField nameField = new TextField();
        formLayout.add(nameField, 1, 0);

        formLayout.add(new Label("Father Name:"), 0, 1);
        TextField fatherNameField = new TextField();
        formLayout.add(fatherNameField, 1, 1);

        formLayout.add(new Label("CNIC:"), 0, 2);
        TextField cnicField = new TextField();
        formLayout.add(cnicField, 1, 2);

        formLayout.add(new Label("Date of Birth:"), 0, 3);
        DatePicker datePicker = new DatePicker();
        Label ageLabel=new Label();
        formLayout.add(datePicker, 1, 3);
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, java.time.LocalDate oldValue, java.time.LocalDate newValue) {
                if (newValue != null) {
                    // Calculate the age
                    int age = calculateAge(newValue);
                    ageLabel.setText("Your age: " + age);
                    formLayout.add(ageLabel,2,3);
                }
            }
        });

        formLayout.add(new Label("Gender:"), 0, 4);
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        formLayout.add(genderBox, 1, 4);

        formLayout.add(new Label("City:"), 0, 5);
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Islamabad", "Karachi","Multan","Faisalabad","Peshawar","Hyderabad");
        formLayout.add(cityComboBox, 1, 5);

        // Add image and file chooser
        VBox rightLayout = new VBox(10);
        rightLayout.setPadding(new Insets(20));

        FileInputStream inputStream=new FileInputStream("D:\\Java Programes\\IntelliJ Idea\\Data_Collection\\Images\\fileChoser.png");
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);

//        Label imageLabel = new Label(imageView);
//        imageLabel.setStyle("-fx-border-color: black; -fx-min-width: 100px; -fx-min-height: 100px;");
        rightLayout.getChildren().add((imageView));
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        Label label=new Label("No File are selected");
        FileChooser fileChooser=new FileChooser();
        Button fileChooserButton = new Button("File Chooser");
        rightLayout.getChildren().add(fileChooserButton);
        EventHandler<ActionEvent> event = new EventHandler <>() {

                    public void handle(ActionEvent e)
                    {

                        // get the file selected
                        File file = fileChooser.showOpenDialog(stage);

                        if (file != null) {

                            label.setText(file.getAbsolutePath()
                                    + "  selected");
                        }
                    }
                };

        fileChooserButton.setOnAction(event);


        formLayout.setPadding(new Insets(20));
        Button saveButton = new Button("Save");
        formLayout.add(saveButton,0,7);
        saveButton.setOnAction(e-> stage.hide());


        //HBox bottomLayout = new HBox(10);
       // bottomLayout.setPadding(new Insets(20));
        Label outro =new Label("Produced by M.Safi-Ur-Rehman (SP24-BSE-B-083)");
        outro.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        outro.setPadding(new Insets(5));
        outro.setMaxWidth(Double.MAX_VALUE);
        outro.setStyle("-fx-alignment: center; -fx-background-color: DARKCYAN;");
        //bottomLayout.getChildren().add(outro);
        mainLayout.setBottom(outro);

//        Button saveButton1 = new Button("Save");
//        bottomLayout.getChildren().add(saveButton1);
//        saveButton.setOnAction(e-> stage.hide());

        // Set the form and bottom layout in the center and bottom of mainLayout
        mainLayout.setCenter(formLayout);
        mainLayout.setRight(rightLayout);
        //mainLayout.setBottom(bottomLayout);

        // Create the scene
        Scene scene = new Scene(mainLayout, 600, 400);

        // Set the stage
        stage.setTitle("Data Collection Application Produced by M.Safi-Ur-Rehman (SP24-BSE-083)");
        stage.setScene(scene);
        stage.show();
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
