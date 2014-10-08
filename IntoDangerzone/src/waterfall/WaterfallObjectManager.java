package waterfall;

import java.util.ArrayList;

import physics.PhysicsObject;

public class WaterfallObjectManager {
	private ArrayList<PhysicsObject> objects = new ArrayList<PhysicsObject>();
	
	public WaterfallObjectManager() {
		
	}
	
	public ArrayList<PhysicsObject> getObjects() {
		return this.objects;
	}
	
	void addObject(PhysicsObject object) {
		this.objects.add(object);
	}

}
