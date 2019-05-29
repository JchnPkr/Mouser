package dance;

import java.util.ArrayList;
import java.util.List;

import dance.danceStyle.Reggaeton;
import dance.danceStyle.Robot;
import dance.danceStyle.RockU;
import dance.danceStyle.Waltz;

public class DanceFactory {
	public static List<DancePattern> getAvailableStyles() {
		List<DancePattern> patternList = new ArrayList<DancePattern>();
		patternList.add(new Robot());
		patternList.add(new RockU());
		patternList.add(new Waltz());
		patternList.add(new Reggaeton());

		return patternList;
	}
}
