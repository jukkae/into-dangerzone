package waterfall;

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
		for (WaterfallObject object : this.objectManager.getObjects()) {
			object.z += 0.1;
		}
	}

}
