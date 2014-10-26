package waterfall;

import processing.core.PApplet;
import graphics.Renderer;

public class WaterfallRenderer extends Renderer {

	protected Waterfall waterfall;

	public WaterfallRenderer(PApplet parent, Waterfall waterfall) {
		super(parent);
		this.waterfall = waterfall;
	}

	@Override
	public void render() {
		float x0 = 0;
		float y0 = 0;
		float z0 = 0;

		parent.pushMatrix();
		parent.translate(-(parent.width / 2), 0, 0);

		for (WaterfallObject object : waterfall.particles) {
			float x = object.x;
			float y = object.y;
			float z = object.z;

			parent.line(x0, y0, z0, x, y, z);
			x0 = x;
			y0 = y;
			z0 = z;
		}

		parent.popMatrix();
	}

}
