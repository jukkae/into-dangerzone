package scenes.attractors;

import java.util.ArrayList;

import math.Vector2D;
import processing.core.PApplet;

public class AttractorsRenderer {

	private PApplet applet;
	private ArrayList<Satellite> satellites;

	public AttractorsRenderer(PApplet applet, ArrayList<Satellite> satellites) {
		this.applet = applet;
		this.satellites = satellites;
	}

	public void render() {
		for (Satellite s : satellites) {
			applet.stroke(0, 0, 0, 7);
			applet.fill(0, 0, 0, 7);
			applet.pushMatrix();
			applet.translate(-applet.width / 2, -applet.height / 2);
			applet.translate(s.location.getX(), s.location.getY());
			applet.ellipse(0, 0, 5, 5);
			applet.popMatrix();
		}
	}

	public void wipe() {
		applet.background(255);
	}
}
