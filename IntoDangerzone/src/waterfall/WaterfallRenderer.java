package waterfall;

import core.InputProvider;
import processing.core.PApplet;
import graphics.Renderer;

public class WaterfallRenderer extends Renderer {

	protected InputProvider<float[]> inputProvider;
	
	protected Waterfall waterfall;
	
	public WaterfallRenderer(PApplet parent, Waterfall waterfall) {
		super(parent);
		this.waterfall = waterfall;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}
	
	public InputProvider<float[]> getInputProvider() {
		return inputProvider;
	}

	public void setInputProvider(InputProvider<float[]> inputProvider) {
		this.inputProvider = inputProvider;
	}

}
