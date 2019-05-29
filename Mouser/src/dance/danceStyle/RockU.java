package dance.danceStyle;

import java.util.ArrayList;
import java.util.List;

import dance.DancePattern;
import dance.Step;

public class RockU extends DancePattern {
	
	public RockU() {
		stepOrder = new ArrayList<Step>();
		stepOrder.add(new Step(0, 1));
		stepOrder.add(new Step(0, 1));
		stepOrder.add(new Step(0, -2));
		stepOrder.add(new Step(0, 0));
	}
	
	public List<Step> getDancePattern() {
		return stepOrder;
	}
}
