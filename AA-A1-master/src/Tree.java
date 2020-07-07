
public class Tree {

	Proc pr;
	Tree left;
	Tree right;
	
	public Tree(String procLabel, int vt)
	{
		pr=new Proc(procLabel, vt){
			
		};
		this.left=null;
		this.right=null;
	}

}
