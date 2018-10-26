package com.micycle.processing;

import com.micycle.javafx.App;
import com.micycle.javafx.Controller;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import processing.core.PApplet;
import processing.core.PSurface;
import processing.javafx.PSurfaceFX;

public class MyPApplet extends PApplet {

    public int bgColor = 255;
    private Label framecount, framerate, canvaswidth;

    @Override
    protected PSurface initSurface() {
        g = createPrimaryGraphics();
        PSurface genericSurface = g.createSurface();
        PSurfaceFX fxSurface = (PSurfaceFX) genericSurface;
        fxSurface.sketch = this;
        App.surface = fxSurface; // todo remove?
        Controller.surface = fxSurface;

        new Thread(() -> Application.launch(App.class)).start();

        while (fxSurface.stage == null) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }

        this.surface = fxSurface;
        Canvas canvas = (Canvas) surface.getNative();
        framecount = (Label) canvas.getScene().lookup("#frameCount");
        framerate = (Label) canvas.getScene().lookup("#frameRate");
        canvaswidth = (Label) canvas.getScene().lookup("#canvasWidth");
        return surface;
    }

    @Override
    public void settings() {
        size(0, 0, FX2D);
    }

    @Override
    public void setup() {
        Controller.p = this;
        background(255);
        strokeWeight(5);
    }

    @Override
    public void draw() {
        framecount.setText(String.valueOf(frameCount));
        framerate.setText(String.valueOf(frameRate));
        canvaswidth.setText(String.valueOf(width));

    }

    @Override
    public void mouseDragged() {
        line(mouseX, mouseY, pmouseX, pmouseY);
    }

    public void redraw() {
        background(bgColor);
    }
}
