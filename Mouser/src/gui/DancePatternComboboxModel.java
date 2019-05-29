package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import dance.DancePattern;

public class DancePatternComboboxModel implements MutableComboBoxModel<DancePattern> {
	private List<DancePattern> patternList = new ArrayList<DancePattern>();
	private int index = -1;

	@Override
	public void setSelectedItem(Object anItem) {
		for(int i = 0; i< patternList.size(); i++)
        {
            if(patternList.get(i).equals(((DancePattern) anItem)))
            {
                index = i;
                break;
            }
        }
	}

	@Override
	public Object getSelectedItem() {
		if(index >= 0)
        {
            return patternList.get(index);
        }
        else
        {
            return "";
        }
	}

	@Override
	public int getSize() {
		return patternList.size();
	}

	@Override
	public DancePattern getElementAt(int index) {
		return index >= 0 ? patternList.get(index) : null;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addElement(DancePattern item) {
		patternList.add(item);
		++index;
	}

	@Override
	public void removeElement(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertElementAt(DancePattern item, int index) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeElementAt(int index) {
		// TODO Auto-generated method stub
		
	}	
}
