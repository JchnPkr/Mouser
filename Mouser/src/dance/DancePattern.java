package dance;

import java.util.List;

public abstract class DancePattern {
	protected List<Step> stepOrder;
	
	public abstract List<Step> getDancePattern();
	
	public String toString() {
		return getClass().getSimpleName();
	}
}
