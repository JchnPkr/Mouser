package dance.danceStyle;

import java.util.ArrayList;
import java.util.List;

import dance.DancePattern;
import dance.Step;

public class Waltz extends DancePattern {
	
	public Waltz() {
		stepOrder = new ArrayList<Step>();
		stepOrder.add(new Step(0, -1));
		stepOrder.add(new Step(1, 0));
		stepOrder.add(new Step(-1, 1));
	}
	
	public List<Step> getDancePattern() {
		return stepOrder;
	}
}
