package com.groundanchor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.layout.GridPane.REMAINING;

public class NumberSum extends Application {
    //declare and instantiate instance members
    private Double[] myArray = new Double[3];
    private String[] buttonText = {"first", "second", "third"};
    private Integer counter = 0;

    //blank constructor for now - jvm will do this automatically.
    //super constructor for Application called as this class extends Application
    public NumberSum() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //declare local vars
        Label enterNumberL, errorBox;
        Text sceneTitle;
        TextField enterNumberF;
        Button submitNumber;
        Stage window;

        try {
            window = primaryStage;
            window.setTitle("Welcome to the number average(r)!");
            //create layout
            GridPane layout = new GridPane();

            //set padding for the objects on the grid pane
            layout.setHgap(5);
            layout.setVgap(10);
            layout.setPadding(new Insets(0, 10, 0, 10));



            //create instances of our objects, labels and fields
            sceneTitle = new Text("Enter 3 numbers and I will calculate the average");
            enterNumberL = new Label(setupNextNumber());
            enterNumberF = new TextField();
            submitNumber = new Button("Enter");
            errorBox = new Label();

            //now we can add them to the pane
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(enterNumberL, 0, 1);
            layout.add(enterNumberF, 1, 1);
            layout.add(submitNumber, 0, 3, 2, 1);

            //we need to un anchor the max button size to allow it to span columns
            submitNumber.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            //set horizontal alignment on one specific node - sceneTitle (instructions)
            GridPane.setHalignment(sceneTitle, HPos.CENTER);
            //set column width for label and text entry
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(85);
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(15);
            layout.getColumnConstraints().addAll(col1, col2);
            //deal with button action
            submitNumber.setOnAction((ActionEvent event) -> {

                if (counter != 3) {
                    layout.getChildren().remove(errorBox);
                    String numberEntered = enterNumberF.getText();
                    enterNumberF.clear();
                    try {
                        double numberEnteredDbl = Double.parseDouble(numberEntered);
                        myArray[counter] = numberEnteredDbl;
                        counter++;
                        if (counter == 3) {
                            Double averageValueF = calcDisplayAverage();
                            System.out.println(averageValueF);
                            Label averageL = new Label("The average is " + averageValueF);
                            layout.getChildren().removeAll(sceneTitle, enterNumberL, enterNumberF, errorBox, submitNumber);
                            layout.add(averageL, 0, 1,2,1);
                        } else {
                            enterNumberL.setText(setupNextNumber());
                            enterNumberF.requestFocus();
                            layout.getChildren().remove(enterNumberL);
                            layout.add(enterNumberL, 0, 1);
                        }
                    } catch (Exception e) {
                        String errorField = "Please enter valid number!";
                        errorBox.setText(errorField);
                        layout.add(errorBox, 0, 2, 2, 1);
                        GridPane.setHalignment(errorBox, HPos.CENTER);
                    }
                }
            });
            //create scene and attach layout to scene and provide dimensions
            Scene scene = new Scene(layout, 350, 125);
            //attach scene to window
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("App failed to bootstrap...");
        }
    }

    private Double calcDisplayAverage() {
        double sum = 0;
        double average = 0;
        for (double n : myArray)
            sum = sum + n;
        average = sum / myArray.length;
        return average;
    }

    private String setupNextNumber() {
        return "Enter " + buttonText[counter] + " number";
    }

}

