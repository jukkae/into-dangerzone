package waterfall;

import physics.PhysicsObject;

public class WaterfallEngine {
	private WaterfallObjectManager objectManager;

	public WaterfallEngine() {
		this(new WaterfallObjectManager());
	}

	public WaterfallEngine(WaterfallObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	public WaterfallObjectManager getWaterfallObjectManager() {
		return this.objectManager;
	}

	public void step() {
		for (PhysicsObject object : this.objectManager.getObjects()) {
			
		}
	}

}
