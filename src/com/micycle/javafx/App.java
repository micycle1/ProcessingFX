package com.micycle.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import processing.javafx.PSurfaceFX;

public class App extends Application {

    public static PSurfaceFX surface;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProcessingFX.fxml"));
        Parent root = loader.load();
        Controller.stage = primaryStage;
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("ProcessingFX Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        surface.stage = primaryStage;
        Controller.stage = primaryStage;
    }
}
