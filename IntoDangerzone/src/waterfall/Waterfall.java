package waterfall;

import java.util.ArrayList;

import core.InputProvider;

public class Waterfall {
	protected ArrayList<WaterfallObject> particles = new ArrayList<WaterfallObject>();
	protected WaterfallObjectManager objectManager;
	protected int objectCount;
	protected InputProvider<float[]> inputProvider;

	public Waterfall(WaterfallObjectManager objectManager, int objectCount, InputProvider<float[]> inputProvider) {
		this.objectManager = objectManager;
		this.objectCount = objectCount;
		this.inputProvider = inputProvider;

		float x = 0;
		float y = 100;
		float z = 0;

		// initialize objects
		for (int i = 0; i < objectCount; i++) {
			x = 0;
			for (int j = 0; j < 128; j++) {
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
			obj.z += 0.1;
			if (obj.z > 512) {
				//respawn
				obj.z = 0;
				obj.y = -input[(int)obj.x/4];
			}
		}
	}
}
