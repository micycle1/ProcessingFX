# ProcessingFX
### Description
A basic project showing how a Processing sketch and a JavaFX window (a stage loaded from an *.FXML* file) may be combined/integrated,
and how each of the two components may interact with each other.

### Extending
If you use this code as inspiration, ensure you download the [*core-10.jar file*](https://github.com/micycle1/ProcessingFX/blob/master/lib/core-10.jar)
and use this in place of your existing Processing library: *core.jar*. 

*core-10.jar* is a modified version of the Processing library in which there have been minor modifications to the *PSurfaceFX* and *PGraphicsFX2D* classes. 
The changes allow us to launch Processing embedded within our own *.....extends Application* (JavaFX stage) class, rather than using the stage Processing would otherwise
create during the sketch's initialisation.

### Methodology (simple)
Before its call to setup(), a PApplet in FX2D rendering mode creates a JavaFX *stage*, *scene* & *stackPane* and embeds the drawing canvas (a JavaFX *Canvas* object) within the stackPane.
In this example project we circumvent this step (enabled by using *core-10.jar*), creating a customised stage (from loading the FXML file) and then embedding the PApplet's drawing canvas into this stage.

## Features
I have included examples of the sketch modifying the UI, and the UI modifying the sketch, so see can see how the two components can interact.
See the headings below for more information about each:

### JavaFX UI --> Processing
There are 2 JavaFX sliders and a colour-picker that modify sketch parameters (the colour of the pen). Behind the scenes, these UI elements trigger `@FXML` decorated methods
in the *Controller.java* class, which in turn call *strokeColor()* on the sketch (*myPApplet.class*) to change pen colour. The method in the controller class that each UI element
calls is prescribed with with `onAction` tag, found accompanying each in the *.FXML* file.

### Processing --> JavaFX UI
In the example project, the sketch updates 3 text labels found in the *Info* tab of the JavaFX UI: frameCount, frameRate and canvasWidth.
The text label objects are exposed in the *myPApplet* class by looking up the `fx:id` (prescribed in the *.FXML* file) of each element in the scene.
The labels are then updated during the *draw()* loop of the sketch.

## Screenshot
The stage is a splitPane, with an *Accordion* on the left and a *StackPane* on the right.
The drawing canvas is added as a node to the *StackPane*. By adding to a stackpane, it is possible to draw JavaFX primitives on top the of the canvas.
<h1 align="center">
<img src="/assets/screen.PNG"/>
</h1>
