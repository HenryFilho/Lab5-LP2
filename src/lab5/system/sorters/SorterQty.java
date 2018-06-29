package lab5.system.sorters;

import java.util.Comparator;

import lab5.system.control.Scenario;

public class SorterQty implements Comparator<Scenario> {

	@Override
	public int compare(Scenario s1, Scenario s2) {
		return s2.getBetsQty() - s1.getBetsQty();
	}

}
