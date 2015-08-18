package scenes.attractors;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.Random;

import math.Vector2D;
import audio.BeatListener;
import audio.ZcrListener;
import ddf.minim.AudioSource;
import processing.core.PApplet;

public class AttractorsScene extends core.Scene {

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
		for (int i = 0; i < 10; i++) {
			Satellite s = new Satellite(rand.nextFloat() * applet.width,
					rand.nextFloat() * applet.height, applet.width, applet.height);
			satellites.add(s);
		}

		for (int i = 0; i < 3; i++) {
			Vector2D location = new Vector2D(rand.nextFloat() * applet.width,
					rand.nextFloat() * applet.height);
			Attractor a = new Attractor(location);
			attractors.add(a);
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
		// TODO Auto-generated method stub
		attractorsRenderer.wipe();
	}

	@Override
	public void deactivated() {
		// TODO Auto-generated method stub

	}

}
