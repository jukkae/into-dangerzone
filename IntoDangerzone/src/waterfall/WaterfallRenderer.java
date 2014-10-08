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

		for (WaterfallObject object : waterfall.particles) {
			float x = object.x;
			float y = object.y;
			float z = object.z;

			parent.point(x, y, z);
		}
	}

}
