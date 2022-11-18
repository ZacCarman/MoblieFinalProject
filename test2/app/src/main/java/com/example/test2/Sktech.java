package com.example.test2;

import processing.core.PApplet;

class Sketch extends PApplet {
    public void settings() {
        size(600, 600);
    }

    public void setup() {
        //fullScreen();
//        frameRate(1);
        textFont(createFont("SansSerif", 30 * displayDensity));
        fill(255);
    }

    public void draw() {
//        background(0);

        if (mousePressed) {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}