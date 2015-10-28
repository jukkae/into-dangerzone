package scenes.spectrogram;

import java.util.ArrayList;

import ddf.minim.AudioSource;
import processing.core.PApplet;
import graphics.Renderer;

public class SpectrogramRenderer extends Renderer {

	private AudioSource audioSource;
	private PApplet applet;
	private int width;
	private int height;

	public SpectrogramRenderer(PApplet parent, AudioSource audioSource) {
		super(parent);
		this.audioSource = audioSource;
		this.applet = parent;
		width = applet.width;
		height = applet.height;
	}

	@Override
	public void render() {
		applet.background(0);

	}

}
