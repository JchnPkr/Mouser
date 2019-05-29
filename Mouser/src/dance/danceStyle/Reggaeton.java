package dance.danceStyle;

import java.util.ArrayList;
import java.util.List;

import dance.DancePattern;
import dance.Step;

public class Reggaeton extends DancePattern {
	
	public Reggaeton() {		
		stepOrder = new ArrayList<Step>();
		stepOrder.add(new Step(-1, 0));
		stepOrder.add(new Step(0, 0));
		stepOrder.add(new Step(0, 0));
		stepOrder.add(new Step(-1, 0));
		stepOrder.add(new Step(0, 0));
		stepOrder.add(new Step(0, 0));
		stepOrder.add(new Step(+2, 0));
		stepOrder.add(new Step(0, 0));
	}
	
	public List<Step> getDancePattern() {
		return stepOrder;
	}
}
