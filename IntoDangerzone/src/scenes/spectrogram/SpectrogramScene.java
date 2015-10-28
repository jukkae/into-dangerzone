package scenes.spectrogram;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import ddf.minim.AudioSource;
import audio.AudioAnalyser;
import processing.core.PApplet;

public class SpectrogramScene extends core.Scene implements KeyEventDispatcher {

	private SpectrogramRenderer renderer;
	private AudioAnalyser audioAnalyser;

	public SpectrogramScene(PApplet parent, AudioSource audioSource) {
		super(parent);
		renderer = new SpectrogramRenderer(parent, audioSource);
		this.audioAnalyser = new AudioAnalyser(parent, audioSource);
	}

	@Override
	public void update(float dtSeconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		renderer.render();
	}

	@Override
	public void activated() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(this);
	}

	@Override
	public void deactivated() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.removeKeyEventDispatcher(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		switch (e.getID()) {
		case KeyEvent.KEY_PRESSED:
			keyPress(e.getKeyCode());
			break;
		}
		return false;
	}

	private void keyPress(int code) {
		// TODO Auto-generated method stub
		switch (code) {
		default:
			break;
		}
	}

}
