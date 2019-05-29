package dance.danceStyle;

import java.util.ArrayList;
import java.util.List;

import dance.DancePattern;
import dance.Step;

public class Robot extends DancePattern {
	
	public Robot() {		
		stepOrder = new ArrayList<Step>();
		stepOrder.add(new Step(-1, 0));
		stepOrder.add(new Step(1, 0));
	}
	
	public List<Step> getDancePattern() {
		return stepOrder;
	}
}
