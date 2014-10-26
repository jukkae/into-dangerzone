package waterfall;

import java.util.ArrayList;
import java.lang.Math;

import core.InputProvider;

public class Waterfall {
	protected ArrayList<WaterfallObject> particles = new ArrayList<WaterfallObject>();
	protected WaterfallObjectManager objectManager;
	protected int objectCount;
	protected int rowWidth;
	protected InputProvider<float[]> inputProvider;

	public Waterfall(WaterfallObjectManager objectManager, int objectCount, InputProvider<float[]> inputProvider) {
		this.objectManager = objectManager;
		this.objectCount = objectCount;
		this.inputProvider = inputProvider;

		float x = 0;
		float y = 0;
		float z = 0;
		
		rowWidth = 256;

		// initialize objects
		// TODO 2D array
		for (int i = 0; i < objectCount; i++) {
			x = 0;
			for (int j = 0; j < 256; j++) {
				WaterfallObject obj = new WaterfallObject(x, y, z);
				particles.add(obj);
				objectManager.addObject(obj);

				x += 4;
				i++;
			}
			z -= 4;
		}
	}

	public void update() {
		float[] input = inputProvider.readInput();
		for (WaterfallObject obj : particles) {
			obj.z += 1;
			if (obj.z > 512) {
				//respawn
				obj.z = 0;
				obj.y = -input[(int)obj.x/4];
				//convert to log scale (dB)
				//TODO check if this is correct
				obj.y = (float) (10 * Math.log10(Math.abs(obj.y*obj.y)));
			}
		}
	}
}
