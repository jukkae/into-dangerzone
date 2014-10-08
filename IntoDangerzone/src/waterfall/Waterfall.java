package waterfall;

import java.util.ArrayList;


public class Waterfall {
	protected ArrayList<WaterfallObject> particles = new ArrayList<WaterfallObject>();
	protected WaterfallObjectManager objectManager;
	
	public Waterfall(WaterfallObjectManager objectManager){
		this.objectManager = objectManager;
	}
	
	public void update(){
		// TODO fill this
	}
}
