package scenes.mandelbrot;

import java.util.Random;

import audio.AudioAnalyser;
import processing.core.PApplet;

//Adapted from code by J. Tarbell
public class Mandelbrot extends core.Scene {

	AudioAnalyser audioAnalyser;
	PApplet parent;
	int dim = 800; // screen dimensions (square window)
	int bailout = 1; // number of iterations before bail
	int plots = 100; // number of plots to execute per frame (x30 = plots per
						// second)

	// 2D array to hold exposure values
	int[] exposure = new int[dim * dim];
	int maxexposure; // maximum exposure value
	int exposures = 0;

	boolean drawing;

	public Mandelbrot(PApplet parent, AudioAnalyser audioAnalyser) {
		super(parent);
		this.parent = parent;
		this.audioAnalyser = audioAnalyser;
	}

	@Override
	public void update(float dtSeconds) {
	}

	@Override
	public void render() {
		plotPlots();
		findMaxExposure();
		renderBrot();
		// show exposure value
		parent.fill(255);
		parent.textSize(32);
		parent.text("bailout:  " + bailout + "    exposures: " + exposures
				+ "    plots: " + plots, 0, 0);
		plots+=10;
		bailout += 1;
	}

	void plotPlots() {
		float x, y;
		// iterate through some plots
		for (int n = 0; n < plots; n++) {
			// Choose a random point in same range
			Random rand = new Random();
			x = rand.nextFloat() * 3 - 2;
			y = (float) ((rand.nextFloat() * 3) - 1.5);
			if (iterate(x, y, false)) {
				iterate(x, y, true);
				exposures++;
			}
		}
	}

	void renderBrot() {
		// draw to screen
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				float ramp = (float) (exposure[i * dim + j] / (maxexposure / 2.5));
				// blow out ultra bright regions
				if (ramp > 1) {
					ramp = 1;
				}
				int c = parent.color((ramp * 255), (ramp * 255), (ramp * 255));
				parent.set(j, i, c);
			}
		}
	}

	// Iterate the Mandelbrot and return TRUE if the point exits
	// Also handle the drawing of the exit points
	boolean iterate(float x0, float y0, boolean drawIt) {
		float x = 0;
		float y = 0;
		float xnew, ynew;
		int ix, iy;

		for (int i = 0; i < bailout; i++) {
			xnew = x * x - y * y + x0;
			ynew = 2 * x * y + y0;
			if (drawIt && (i > 3)) {
				ix = (int) (dim * (xnew + 2.0) / 3.0);
				iy = (int) (dim * (ynew + 1.5) / 3.0);
				if (ix >= 0 && iy >= 0 && ix < dim && iy < dim) {
					// rotate and expose point
					exposure[ix * dim + iy]++;
				}

			}
			if ((xnew * xnew + ynew * ynew) > 4) {
				// escapes
				return true;
			}
			x = xnew;
			y = ynew;
		}
		// does not escape
		return false;
	}

	void findMaxExposure() {
		// assume no exposure
		maxexposure = 0;
		// find the largest density value
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				maxexposure = PApplet.max(maxexposure, exposure[i * dim + j]);
			}
		}
	}

}
