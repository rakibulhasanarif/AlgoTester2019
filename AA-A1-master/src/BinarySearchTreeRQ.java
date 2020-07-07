import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

	private Tree tr;
	int vtime;
	int sum=0;
	static Tree[] storelist = new Tree[1000000];
	int num;
	public BinarySearchTreeRQ()
	{

        tr = null;

    }
	@Override
	 public void enqueue(String procLabel, int vt) 
	 {
	        tr = buildtree(tr, procLabel, vt);
	        storelist[num++] = new Tree(procLabel, vt);
	 }
	  private Tree buildtree(Tree tret, String procLabel, int vt) {
	        if (tret == null) {
	            tret=new Tree(procLabel, vt);
	            return tret;
	        }

	        if (vt < tret.pr.getVT()) 
	        {
	            tret.left = buildtree(tret.left, procLabel, vt);
	        }
	        
	        else if (vt > tret.pr.getVT()) 
	        {
	            tret.right = buildtree(tret.right, procLabel, vt);
	        } 
	        
	        else 
	        {
	            
	            return tret;
	        }

	        return tret;
	    }

	@Override
	  public String dequeue()
	  {
		  int id=lowestrun(tr);
		  tr=deleterecord(tr,id);
		  if(tr!=null)
		  {
			  return tr.pr.getProcLabel();
		  }
		  else
		  {
			  return null;
		  }
	  }
	  
	  Tree deleterecord(Tree trnt,int id)
	  {
		  if(tr==null)
		  {
			  return tr;
		  }
		  else if(id<trnt.pr.getVT())
		  {
			  trnt.left=deleterecord(trnt.left, id);
		  }
		  else if(id>trnt.pr.getVT())
		  {
			  trnt.right=deleterecord(trnt.right, id);
		  }
		  else
		  {
			 if(trnt.left==null)
			 {
				 return trnt.right;
			 }
			 else if(trnt.right==null)
			 {
				 return trnt.left;
			 }
			 
			 trnt.pr.setVT(lowestrun(trnt.right));
			 trnt.right=deleterecord(trnt.right,trnt.pr.getVT());
		  }
		  
		return trnt;
	  }
	  

	  private int lowestrun(Tree trnt)
	  {
		  int min=trnt.pr.getVT();
		  while(trnt.left!=null)
		  {
			  min=trnt.left.pr.getVT();
			  trnt=trnt.left;
		  }
		return min;
		  
	  }

	@Override
	  public boolean findProcess(String procLabel) {
			Tree current = tr;
			try {
				for (int i = 0; i < BinarySearchTreeRQ.storelist.length; i++) {
					if (BinarySearchTreeRQ.storelist[i].pr.getProcLabel().equals(procLabel)) {
						int id = BinarySearchTreeRQ.storelist[i].pr.getVT();
					System.out.println("true");
						while (current != null) {
							if (current.pr.getVT() == id) {
								return true;
							} else if (current.pr.getVT() > id) {
								current = current.left;
							} else {
								current = current.right;
							}
						}

					}

				}
				
			}catch (Exception e) 
			{
				System.out.println("false");
			}
			return false;
		}

	@Override
	  public boolean removeProcess(String procLabel) 
	  {
		  boolean dead= cleared(tr,procLabel);
		  return dead;
		  
	  }
	  public boolean cleared(Tree trnt,String dat)
	  {
		  if(tr==null)
		  {
			  return false;
		  }
		  if(tr.pr.getProcLabel()==dat)
		  {
			  deleterecord(trnt,tr.pr.getVT());
			  return true;
		  }
		  boolean outleft=cleared(trnt.left,dat);
		  if(outleft==true)
		  {
			  deleterecord(tr,trnt.pr.getVT());
			  return true;
		  }
		  boolean outright=cleared(trnt.right,dat);
		  if(outright==true)
		  {
			  deleterecord(tr,trnt.pr.getVT());
			  return true;
		  }
		  else
		  {
			  return outright;
		  }
	  }

	@Override
	public int precedingProcessTime(String procLabel) {

		return -1;

	}

	@Override
	public int succeedingProcessTime(String procLabel) {

		return -1;

	}

	@Override
	  public void printAllProcesses(PrintWriter os) {
	  display(tr,os);
	  os.println();

}
	public void display(Tree root,PrintWriter os) 
	{
		if (root == null) {
			return ;
		}
			display(root.left,os);
	        os.print(root.pr.getProcLabel()+" ");
			display(root.right,os);
	}

} // end of class BinarySearchTreeRQ
