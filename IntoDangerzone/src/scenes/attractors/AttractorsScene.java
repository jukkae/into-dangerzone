package scenes.attractors;

import java.applet.Applet;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import math.Vector2D;
import audio.BeatListener;
import audio.ZcrListener;
import ddf.minim.AudioSource;
import processing.core.PApplet;

public class AttractorsScene extends core.Scene implements KeyEventDispatcher {

	private AttractorsRenderer attractorsRenderer;
	private BeatListener beatListener;
	private ZcrListener zcrListener;

	private ArrayList<Attractor> attractors;
	private ArrayList<Satellite> satellites;

	private Random rand;
	private PApplet applet;

	public AttractorsScene(PApplet parent, AudioSource audioSource) {
		super(parent);
		this.applet = parent;
		this.beatListener = new BeatListener(audioSource);
		this.zcrListener = new ZcrListener(audioSource);

		this.attractors = new ArrayList<Attractor>();
		this.satellites = new ArrayList<Satellite>();
		this.attractorsRenderer = new AttractorsRenderer(parent, satellites);

		this.rand = new Random();

		initialize();
	}

	private void initialize() {
		for (int i = 0; i < 0; i++) {
			addRandomSatellite();
		}

		for (int i = 0; i < 0; i++) {
			Vector2D location = new Vector2D(rand.nextFloat() * applet.width,
					rand.nextFloat() * applet.height);
			float g = rand.nextFloat() * 200;
			Attractor a = new Attractor(location, g);
			attractors.add(a);
		}
	}
	
	private void addRandomSatellite() {
		Satellite s = new Satellite(rand.nextFloat() * applet.width,
				rand.nextFloat() * applet.height, applet.width, applet.height);
		satellites.add(s);
	}
	
	private void removeSatellite() {
		if(satellites.size() > 0) {
			satellites.remove(satellites.size()-1);
		}
	}
	
	private void addRandomAttractor() {
		Vector2D location = new Vector2D(rand.nextFloat() * applet.width,
				rand.nextFloat() * applet.height);
		float g = rand.nextFloat() * 200;
		Attractor a = new Attractor(location, g);
		attractors.add(a);
	}
	
	private void removeAttractor() {
		if(attractors.size() > 0) {
			attractors.remove(attractors.size()-1);
		}
	}

	@Override
	public void update(float dtSeconds) {
		for (int i = 0; i < satellites.size(); i++) {
			Satellite s = satellites.get(i);
			s.update();
			for (int j = 0; j < attractors.size(); j++) {
				Vector2D f = attractors.get(j).getForce(s.getLocation());
				s.applyForce(f);
			}
		}
		return;
	}

	@Override
	public void render() {
		attractorsRenderer.render();
	}

	@Override
	public void activated() {
		attractorsRenderer.wipe();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);

	}

	@Override
	public void deactivated() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
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
		switch (code) {
		case KeyEvent.VK_UP:
			addRandomSatellite();
			break;
		case KeyEvent.VK_DOWN:
			removeSatellite();
			break;
		case KeyEvent.VK_RIGHT:
			addRandomAttractor();
			break;
		case KeyEvent.VK_LEFT:
			removeAttractor();
			break;
		case KeyEvent.VK_SPACE:
			attractorsRenderer.wipe();
			break;
		case KeyEvent.VK_M:
			attractorsRenderer.toggleTrails();
			break;
		}
	}

}
