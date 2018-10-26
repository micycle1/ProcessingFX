package com.micycle.javafx;

import com.micycle.processing.MyPApplet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import processing.javafx.PSurfaceFX;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Communicates JavaFX events back to the running PApplet
 */
public class Controller implements Initializable {

    public static PSurfaceFX surface;
    public static MyPApplet p;
    protected static Stage stage;

    @FXML
    AnchorPane superParent;
    @FXML
    Slider bgBrightness, penSize;
    @FXML
    StackPane processing;
    @FXML
    ColorPicker colorPicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas canvas = (Canvas) surface.getNative();
        surface.fx.context = canvas.getGraphicsContext2D();
        processing.getChildren().add(canvas);
        canvas.widthProperty().bind(processing.widthProperty());
        canvas.heightProperty().bind(processing.heightProperty());

        penSize.valueProperty().addListener((observable, oldValue, newValue) -> {
            p.strokeWeight(newValue.intValue());
        });

        bgBrightness.valueProperty().addListener((observable, oldValue, newValue) -> {
            p.bgColor = newValue.intValue();
            p.redraw();
        });
    }

    @FXML
    private void redPen() {
        p.stroke(255, 0, 0);
    }

    @FXML
    private void greenPen() {
        p.stroke(0, 255, 0);
    }

    @FXML
    private void bluePen() {
        p.stroke(0, 0, 255);
    }

    @FXML
    private void exit() {
        stage.close();
    }

    @FXML
    private void clearCanvas() {
        p.redraw();
    }

    @FXML
    private void pickColor() {
        p.stroke((int) (colorPicker.getValue().getRed() * 255), (int) (colorPicker.getValue().getGreen() * 255),
                (int) (colorPicker.getValue().getBlue() * 255));
    }
}