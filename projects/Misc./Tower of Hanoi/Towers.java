import java.util.ArrayList;
import java.util.Collections;

public class Towers {
	private ArrayList<Integer> leftPeg;
	private ArrayList<Integer> middlePeg;
	private ArrayList<Integer> rightPeg;

	public Towers() {
		leftPeg = new ArrayList<Integer>(5);
		middlePeg = new ArrayList<Integer>(5);
		rightPeg = new ArrayList<Integer>(5);
		for (int i = 5; i > 0; i--) {
			leftPeg.add(i);
		}

	}

	public Towers(ArrayList<Integer> leftPeg, ArrayList<Integer> middlePeg, ArrayList<Integer> rightPeg) {
		this.leftPeg = leftPeg;
		this.middlePeg = middlePeg;
		this.rightPeg = rightPeg;
	}

	public Towers(int rings) {
		leftPeg = new ArrayList<Integer>(rings);
		middlePeg = new ArrayList<Integer>(rings);
		rightPeg = new ArrayList<Integer>(rings);
		for (int i = rings; i > 0; i--) {
			leftPeg.add(i);
		}

	}

	public int getCount() {
		return leftPeg.size() + middlePeg.size() + rightPeg.size();
	}

	public void setTower(int rings) {
		leftPeg.clear();
		for (int i = rings; i > 0; i--) {
			leftPeg.add(i);
		}
	}

	public void reset() {
		int rings = getCount();
		setTower(rings);
		middlePeg.clear();
		rightPeg.clear();
	}

	public boolean isSolved() {
		return rightPeg.size() == getCount();
	}

	private boolean transfer(ArrayList<Integer> arrL1, ArrayList<Integer> arrL2) {
		if ((arrL1.size() == 0 && arrL2.size() == 0) || arrL1.equals(arrL2)) {
			return false;
		}

		if (arrL1.size() != 0 && ((arrL2.size() == 0) || (arrL1.get(arrL1.size() - 1) < arrL2.get(arrL2.size() - 1)))) {
			arrL2.add(arrL1.get(arrL1.size() - 1));
			arrL1.remove(arrL1.size() - 1);
		}

		else {

			arrL1.add(arrL2.get(arrL2.size() - 1));
			arrL2.remove(arrL2.size() - 1);
		}

		return true;
	}

	private ArrayList<Integer> assignInt(int num) {
		if (num == 0) {
			return leftPeg;
		}
		if (num == 1) {
			return middlePeg;
		}
		return rightPeg;
	}

	public boolean makeMove(int one, int two) {
		ArrayList<Integer> arrL1 = assignInt(one);
		ArrayList<Integer> arrL2 = assignInt(two);
		return transfer(arrL1, arrL2);
	}

	private int biggestTotalRing() {
		ArrayList<Integer> arrL = new ArrayList<Integer>();
		arrL.addAll(leftPeg);
		arrL.addAll(middlePeg);
		arrL.addAll(rightPeg);
		return 2 * Collections.max(arrL);
	}
	
	private String spaces(ArrayList<Integer> arrL, ArrayList<Integer> arrL2, int i) {
		String str = "";
		int numTabs = biggestTotalRing() / 8 + 1;
		if (arrL2.size() > i) {
			for (int j = 0; j < numTabs
					- (arrL2.get(i)*2 + (arrL2.get(0) * 2 / 2) - (arrL2.get(i) * 2 / 2))/8; j++) {
				str += "\t";
			}
			return str;
		}
		for (int j = 0; j < numTabs; j++) {
			str += "\t";
		}

		return str;
	}
	

	private String center(ArrayList<Integer> arrL, int i) {
		String str = "";
		if (i != 0) {
			for (int j = 0; j < (arrL.get(0) * 2 / 2) - (arrL.get(i) * 2 / 2); j++) {
				str += " ";
			}
		}
		return str;
	}

	public String toString() {
		String str = "";
		int rings = getCount();
		for (int i = rings - 1; i >= 0; i--) {
			if (leftPeg.size() > i) {
				str += center(leftPeg, i);
				for (int j = 0; j < leftPeg.get(i) * 2; j++) {
					str += "X";
				}
			} else if (leftPeg.size() == 0) {
				str += "|";
			}

			
			str += spaces(middlePeg, leftPeg, i);
			if (middlePeg.size() > i) {
				str += center(middlePeg, i);
				for (int j = 0; j < middlePeg.get(i) * 2; j++) {
					str += "O";
				}
			} else if (middlePeg.size() == 0) {
				str += "|";
			}
			
			
			
			
			str += spaces(rightPeg, middlePeg, i);
			if (rightPeg.size() > i) {
				str += center(rightPeg, i);
				for (int j = 0; j < rightPeg.get(i) * 2; j++) {
					str += "M";
				}
			}
			else if(rightPeg.size()==0) {
				str+="|";
			}
			

			str += "\n";

		}

		return str;

	}

	public ArrayList<Integer> getLeftPeg() {
		return leftPeg;
	}

	public void setLeftPeg(ArrayList<Integer> leftPeg) {
		this.leftPeg = leftPeg;
	}

	public ArrayList<Integer> getMiddlePeg() {
		return middlePeg;
	}

	public void setMiddlePeg(ArrayList<Integer> middlePeg) {
		this.middlePeg = middlePeg;
	}

	public ArrayList<Integer> getRightPeg() {
		return rightPeg;
	}

	public void setRightPeg(ArrayList<Integer> rightPeg) {
		this.rightPeg = rightPeg;
	}

}
