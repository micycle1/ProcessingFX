/**
 * Stand-alone example of using an FXML file from a Processing .pde sketch.
 * Does not rely on the ProcessingFX library.
 * 
 * Load a new scene from an FXML file and replace the the default scene Processing
 * makes with this new one (during runtime).
 *
 * https://discourse.processing.org/t/fxml-file-integrate-in-processing/18325/10
 */

import java.util.Map;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import processing.javafx.PSurfaceFX;

public void setup() {
  size(800, 800, FX2D);
  strokeWeight(3);
}

protected PSurface initSurface() {
  surface = (PSurfaceFX) super.initSurface();
  final Canvas canvas = (Canvas) surface.getNative();
  final Scene oldScene = canvas.getScene();
  final Stage stage = (Stage) oldScene.getWindow();

  try {
    FXMLLoader loader = new FXMLLoader(Paths.get("C:\\path--to--fxml\\stage.fxml").toUri().toURL()); // abs path to fxml file
    final Parent sceneFromFXML = loader.load();
    final Map<String, Object> namespace = loader.getNamespace();

    final Scene newScene = new Scene(sceneFromFXML, stage.getWidth(), stage.getHeight(), false, 
      SceneAntialiasing.BALANCED);
    final AnchorPane pane = (AnchorPane) namespace.get("anchorPane"); // get element by fx:id

    pane.getChildren().add(canvas); // processing to stackPane
    canvas.widthProperty().bind(pane.widthProperty()); // bind canvas dimensions to pane
    canvas.heightProperty().bind(pane.heightProperty()); // bind canvas dimensions to pane

    Platform.runLater(new Runnable() {
      @Override
        public void run() {
        stage.setScene(newScene);
      }
    }
    );
  } 
  catch (IOException e) {
    e.printStackTrace();
  }
  return surface;
}

public void draw() {
  background(125, 125, 98);
  ellipse(200, 200, 200, 200);
  line(0, 0, width, height);
  line(width, 0, 0, height);
}
