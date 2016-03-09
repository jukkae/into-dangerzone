package scenes.boids;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Random;

import ddf.minim.AudioSource;
import audio.BeatListener;
import processing.core.PApplet;

public class BoidsScene extends core.Scene implements KeyEventDispatcher  {

	private Flock flock;
	private BoidsRenderer boidsRenderer;

	private BeatListener beatListener;

	private Random rand;
	private float newBoidProbability = 0.1f;
	private float newRulesProbability = 0.2f;
	private float newRenderingProbability = 0.03f;

	public BoidsScene(PApplet parent, AudioSource audioSource) {
		super(parent);
		this.parent = parent;
		this.beatListener = new BeatListener(audioSource);
		flock = new Flock(parent);
		boidsRenderer = new BoidsRenderer(parent, flock);

		rand = new Random();

		flock.initialize();
	}

	@Override
	public void update(float dtSeconds) {
		if (beatListener.isSnare()) {
			if (rand.nextFloat() < newBoidProbability) {
				flock.newBoid();
			}
		}
		if (beatListener.isKick()) {
			if (rand.nextFloat() < newRulesProbability) {
				flock.newRules();
			}
		}
		flock.run();
		if (beatListener.isHat()) {
			if (rand.nextFloat() < newRenderingProbability) {
				boidsRenderer.randomRenderingMode();
			}
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		switch(e.getID()) {
		case KeyEvent.KEY_PRESSED:
			keyPress(e.getKeyCode());
			break;
		}
		return false;
	}
	
	private void keyPress(int code) {
		switch(code) {
		case KeyEvent.VK_UP:
			flock.newBoid();
			break;
		case KeyEvent.VK_DOWN:
			flock.removeBoid();
			break;
		case KeyEvent.VK_SPACE:
			flock.newRules();
			break;
		}
	}

	@Override
	public void render() {
		boidsRenderer.render();
	}

	@Override
	public void activated() {
		// TODO Auto-generated method stub
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	@Override
	public void deactivated() {
		// TODO Auto-generated method stub
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
	}

}
