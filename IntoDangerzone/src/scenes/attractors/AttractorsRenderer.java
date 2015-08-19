package scenes.attractors;

import java.util.ArrayList;

import math.Vector2D;
import processing.core.PApplet;

public class AttractorsRenderer {

	public enum RenderMode {
		TINY, WEIGHTED, AGE
	};

	private PApplet applet;
	private ArrayList<Satellite> satellites;

	public RenderMode renderMode;
	public boolean trails;
	private boolean wipe = true;
	private boolean invertColors;

	public AttractorsRenderer(PApplet applet, ArrayList<Satellite> satellites) {
		this.applet = applet;
		this.satellites = satellites;
		this.renderMode = RenderMode.TINY;
		this.trails = false;
	}

	public void render() {
		if (wipe) {
			wipe();
			wipe = false;
		}
		if (!trails) {
			wipe();
		}

		if (invertColors) {
			applet.stroke(255, 255, 255, 255);
			applet.fill(255, 255, 255, 255);

		} else {
			applet.stroke(0, 0, 0, 255);
			applet.fill(0, 0, 0, 255);
		}

		for (Satellite s : satellites) {
			applet.pushMatrix();
			applet.translate(-applet.width / 2, -applet.height / 2);
			applet.translate(s.location.getX(), s.location.getY());
			// applet.point(0, 0);
			applet.ellipse(0, 0, 3, 3);
			applet.popMatrix();
		}
	}

	public void changeRenderMode() {

	}

	public void toggleTrails() {
		trails = !trails;
	}

	public void toggleColorScene() {
		invertColors = !invertColors;
		wipe();
	}

	public void wipe() {
		wipe = true;
		if (invertColors)
			applet.background(0);
		else
			applet.background(255);
		return;
	}
}
