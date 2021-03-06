package scenes.gameoflife;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Random;

import audio.AudioAnalyser;
import audio.BeatListener;
import math.Vector3D;
import graphics.Camera;
import core.Scene;
import ddf.minim.AudioSource;
import processing.core.PApplet;

public class GameOfLifeScene extends Scene implements KeyEventDispatcher {

	private enum Mode {
		AUDIO, MANUAL;
	}

	private Mode mode;

	private float generationTimer;
	private float stepDuration;

	private GameOfLife gol;
	private GameOfLifeRenderer golRenderer;

	private Camera camera;

	private Random rand;
	private boolean[][] bomb;

	private BeatListener beatListener;

	public static final RuleSet[] RULE_SETS = { RuleSet.AMOEBA,
			RuleSet.ASSIMILATION, RuleSet.COAGULATIONS, RuleSet.CORAL,
			RuleSet.DAY_AND_NIGHT, RuleSet.DIAMOEBA, RuleSet.GNARL,
			RuleSet.GOL, RuleSet.HIGH_LIFE, RuleSet.LIFE_WITHOUT_DEATH,
			RuleSet.LONG_LIFE, RuleSet.MAZE, RuleSet.MAZECTRIC, RuleSet.MOVE,
			RuleSet.PSEUDO_LIFE, RuleSet.REPLICATOR, RuleSet.SEEDS_TWO,
			RuleSet.SERVIETTES, RuleSet.STAINS, RuleSet.THREE_FOUR_LIFE,
			RuleSet.TWO_X_TWO, RuleSet.WALLED_CITIES };

	public GameOfLifeScene(PApplet parent, AudioSource audioSource,
			float stepDuration, int columns, int rows) {
		super(parent);

		this.beatListener = new BeatListener(audioSource);

		gol = new GameOfLife(columns, rows);
		gol.seedRandom();

		this.golRenderer = new GameOfLifeRenderer(parent, gol);
		initializeStepTimer(stepDuration);
		this.camera = new Camera(parent);

		this.rand = new Random();

		this.mode = Mode.AUDIO;

		this.bomb = new boolean[3][3];
		bomb[0][1] = true;
		bomb[1][0] = true;
		bomb[1][1] = true;
		bomb[1][2] = true;
		bomb[2][0] = true;
	}

	public GameOfLifeScene(PApplet parent, AudioSource audioSource,
			int columns, int rows) {
		this(parent, audioSource, 0.05f, columns, rows);
	}

	@Override
	public void update(float dtSeconds) {
		generationTimer -= dtSeconds;
		if (generationTimer < 0) {
			generationTimer = stepDuration;
			gol.stepGeneration();
			if (mode == Mode.AUDIO) {
				if (beatListener.isKick()) {
					gol.insertShape(bomb, rand.nextInt(gol.getColumnCount()),
							rand.nextInt(gol.getRowCount()));
				}
				if (beatListener.isHat()) {
					RuleSet ruleSet = RULE_SETS[rand.nextInt(RULE_SETS.length)];
					gol.setRuleSet(ruleSet);
				}
			}
		}
	}

	@Override
	public void render() {
		golRenderer.render();
	}

	private void updateCamera() {
		float fovRadians = (float) Math.toRadians(75); // ?
		float zDistW = (float) ((parent.width / 2.0f) / Math
				.tan(fovRadians / 2.0f));
		float zDistH = (float) ((parent.height / 2.0f) / Math
				.tan(fovRadians / 2.0f));
		float zDist = Math.max(zDistW, zDistH);
		camera.setPosition(new Vector3D(0, 0, zDist));
		camera.setCenter(new Vector3D(0, 0, 0));
		camera.update();
	}

	private void initializeStepTimer(float stepDuration) {
		this.stepDuration = stepDuration;
		generationTimer = stepDuration;
	}

	@Override
	public void activated() {
		updateCamera();
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

	// TODO yes it's duplicate code
	private void keyPress(int code) {
		switch (code) {
		case KeyEvent.VK_SPACE:
			RuleSet ruleSet = RULE_SETS[rand.nextInt(RULE_SETS.length)];
			gol.setRuleSet(ruleSet);
			break;
		case KeyEvent.VK_B:
			gol.insertShape(bomb, rand.nextInt(gol.getColumnCount()),
					rand.nextInt(gol.getRowCount()));
			break;
		case KeyEvent.VK_C:
			gol.clear();
			break;
		case KeyEvent.VK_M:
			changeMode();
			break;
		}
	}

	public void changeMode() {
		switch (mode) {
		case AUDIO:
			mode = Mode.MANUAL;
			break;
		case MANUAL:
			mode = Mode.AUDIO;
			break;
		}
	}

}
