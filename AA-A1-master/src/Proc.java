
public class Proc {
	
	//General
	private String procLabel;
	private int vt;
	
	//For LinkedList implimentation
	private Proc nextProc;
	
	public Proc(String procLabel, int vt) {
		this.procLabel = procLabel;
		this.vt = vt;
	}
	
	public void setProcLabel(String newLabel) {
		this.procLabel = newLabel;
	}
	
	public String getProcLabel() {
		return this.procLabel;
	}
	
	public void setVT(int newVT) {
		this.vt = newVT;
	}
	
	public int getVT() {
		return this.vt;
	}
	
	//For LinkedList implimentation
	public Proc getNextProc() {
		return this.nextProc;
	}
	
	//For LinkedList implimentation
	public void setNextProc(Proc nextProc) {
		this.nextProc = nextProc;
	}

}
