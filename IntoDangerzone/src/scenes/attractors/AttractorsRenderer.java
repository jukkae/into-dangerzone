package scenes.attractors;

import java.util.ArrayList;

import math.Vector2D;
import processing.core.PApplet;

public class AttractorsRenderer {

	public enum RenderMode {
		TINY, WEIGHTED, AGE
	};

	public enum ColorScheme {
		WHITE_ON_BLACK, BLACK_ON_WHITE
	}

	private PApplet applet;
	private ArrayList<Satellite> satellites;

	public RenderMode renderMode;
	public ColorScheme colorScheme;
	public boolean trails;

	public AttractorsRenderer(PApplet applet, ArrayList<Satellite> satellites) {
		this.applet = applet;
		this.satellites = satellites;
		this.renderMode = RenderMode.TINY;
		this.colorScheme = ColorScheme.BLACK_ON_WHITE;
		this.trails = false;
	}

	public void render() {
		if (!trails) {
			wipe();
		}

		applet.stroke(0, 0, 0, 255);
		applet.fill(0, 0, 0, 255);

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

	}

	public void wipe() {
		if (this.colorScheme == ColorScheme.BLACK_ON_WHITE) {
			applet.background(255);
		} else {
			applet.background(0);
		}
	}
}
