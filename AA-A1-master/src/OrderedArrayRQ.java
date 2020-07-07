import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

	/**
	 * Constructs empty queue
	 */
	private Proc[] procArray;

	public OrderedArrayRQ() {
		this.procArray = new Proc[0];
	}

	@Override
	public void enqueue(String procLabel, int vt) {
		Proc newProc = new Proc(procLabel, vt);
		int equalsIndex = 0;
		int greaterIndex = 0;
		int switchCase = 4;
		boolean equivalent = false;

		// If the array is empty
		if (this.procArray.length == 0) {
			this.procArray = new Proc[] { newProc };
		} else {
			for (int i = 0; i < this.procArray.length; i++) {
				// If there is an equivilent VT in the array, find its highest index.
				if (newProc.getVT() == this.procArray[i].getVT()) {
					equalsIndex = i;
					equivalent = true;
					switchCase = 1;
					// If not, find the index of the first VT in the array which is larger than
					// newProc.VT and break.
				} else if (newProc.getVT() < this.procArray[i].getVT()) {
					greaterIndex = i;
					switchCase = 2;
					break;
					// Else new Proc must have the largest VT so far.
				} else {
					if (!equivalent) {
						switchCase = 3;
					}
				}
			}

			switch (switchCase) {
			case 1:
				// There is one or more equivalent VT values in the array - new entry needs to
				// go behind the largest one.
				Proc[] newArray0 = new Proc[this.procArray.length + 1];
				int targetIndex = equalsIndex + 1;
				int j = 0;
				for (int i = 0; i < newArray0.length; i++) {
					if (j == targetIndex) {
						newArray0[j] = newProc;
						j++;
						if (j == newArray0.length) {
							break;
						}
					}
					newArray0[j] = this.procArray[i];
					j++;
				}
				this.procArray = newArray0;
				break;
			case 2:
				// There are no equivalent VT values but the new VT value is not the largest so
				// far.
				Proc[] newArray1 = new Proc[this.procArray.length + 1];
				// Add new proc before the element at greaterIndex (first element.VT in the
				// array which is larger than newProc.VT).
				int k = 0;
				for (int i = 0; i < this.procArray.length; i++) {
					if (k == greaterIndex) {
						newArray1[k] = newProc;
						k++;
					}
					newArray1[k] = this.procArray[i];
					k++;
				}
				this.procArray = newArray1;
				break;
			case 3:
				// The VT value of the new proc is the largest so far. New proc goes at the end
				// of the array.
				Proc[] newArray2 = new Proc[this.procArray.length + 1];
				for (int i = 0; i < this.procArray.length; i++) {
					newArray2[i] = this.procArray[i];
				}
				newArray2[this.procArray.length] = newProc;
				this.procArray = newArray2;
				break;
			case 4:
				// Error.
				System.out.println("Error - no case match in OrderedArrayRQ switch statement.");
				break;
			}
		}
	}// end of enqueue()

	@Override
	public String dequeue() {
		if (this.procArray.length == 0) {
			return "";
		} else {
			String deletedLabel = this.procArray[0].getProcLabel();
			Proc[] newArray = new Proc[this.procArray.length - 1];
			for (int i = 1; i < this.procArray.length; i++) {
				newArray[i - 1] = this.procArray[i];
			}
			this.procArray = newArray;
			return deletedLabel;
		}
	}// end of dequeue()

	@Override
	public boolean findProcess(String procLabel) {
		boolean procCheck = false;
		for (int i = 0; i < this.procArray.length; i++) {
			if (this.procArray[i].getProcLabel().equals(procLabel)) {
				procCheck = true;
			}
		}
		return procCheck;
	}// end of findProcess()

	@Override
	public boolean removeProcess(String procLabel) {

		Proc[] newArray = new Proc[this.procArray.length - 1];
		boolean procCheck = false;
		int j = 0;
		for (int i = 0; i < this.procArray.length; i++) {
			if (this.procArray[i].getProcLabel().equals(procLabel)) {
				procCheck = true;
				continue;
			}	
			if (j == newArray.length) {
				break;
			}
			newArray[j] = this.procArray[i];
			j++;
		}		
		if (procCheck) {
			this.procArray = newArray;
			return procCheck;
		} else {
			return false;
		}
	}// end of removeProcess()

	@Override
	public int precedingProcessTime(String procLabel) {
		boolean procCheck = false;
		int runningTotal = 0;
		for (int i = 0; i < this.procArray.length; i++) {
			if (this.procArray[i].getProcLabel().equals(procLabel)) {
				procCheck = true;
				break;
			}
			runningTotal += this.procArray[i].getVT();
		}

		if (procCheck) {
			return runningTotal;
		} else {
			return -1;
		}
	}// end of precedingProcessTime()

	@Override
	public int succeedingProcessTime(String procLabel) {
		boolean procCheck = false;
		int runningTotal = 0;

		for (int i = 0; i < this.procArray.length; i++) {
			if (procCheck) {
				runningTotal += this.procArray[i].getVT();
			}
			if (this.procArray[i].getProcLabel().equals(procLabel)) {
				procCheck = true;
			}
		}

		if (procCheck) {
			return runningTotal;
		} else {
			return -1;
		}
	} // end of precedingProcessTime()

	@Override
	public void printAllProcesses(PrintWriter os) {
		String printString = "";
		for (int i = 0; i < this.procArray.length; i++) {
			printString += this.procArray[i].getProcLabel() + " ";
		}
		os.println(printString);
	}

} // end of class OrderedArrayRQ
