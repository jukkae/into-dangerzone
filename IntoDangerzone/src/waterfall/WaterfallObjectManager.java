package waterfall;

import java.util.ArrayList;

public class WaterfallObjectManager {
	private ArrayList<WaterfallObject> objects = new ArrayList<WaterfallObject>();

	public WaterfallObjectManager() {

	}

	public ArrayList<WaterfallObject> getObjects() {
		return this.objects;
	}

	void addObject(WaterfallObject object) {
		this.objects.add(object);
	}

}
