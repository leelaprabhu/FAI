import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Queue;

public class Start{
	static String tiles[]={"A","B","C","D"};
	static int size=4;
	static int noExp=0;
	static int maxFringe=1;
	public static void main(String args[]) throws IOException{
		World world = new World(tiles, size);
		System.out.println("Start");
		int[] A={0,0};
		int[] B={0,1};
		int[] C={0,2};
		int[] D={3,3};
		int[] X={0,3};
		HashMap<String,int[]> start= new HashMap<String,int[]>();
		HashMap<Long, SiNode> track= new HashMap<Long, SiNode>();
		start.put("X", X);
		start.put("A", A);
		start.put("B", B);
		start.put("C", C);
		start.put("D", D);
		
		int[] Ag={2,1};
		int[] Bg={1,1};
		int[] Cg={0,1};
		int[] Dg={2,3};
		int[] Xg={0,3};
		HashMap<String,int[]> goal= new HashMap<String,int[]>();
		goal.put("X", Xg);
		goal.put("A", Ag);
		goal.put("B", Bg);
		goal.put("C", Cg);
		goal.put("D", Dg);
		
		SiNode root =new SiNode(start, world);
		SiNode dest =new SiNode(goal, world);
		System.out.println(root.moveUp().moveUp().getID());
		//System.out.println(root.expand().size());
	
		bfs(root,dest);
		//idfs(dest,root);
		//eidfs(dest,root);
		//astar(root,dest);
		//dfs(root,dest,16);
		//dfs(root,dest,10000);
		//printPath(getPath(dest,root));
		
	}
	
	public static int idfs(SiNode dest, SiNode root){
		boolean isFound=false;
		int i=0;
		while (!isFound){
			i++;
			isFound= dfs(root,dest,i);
		}
		return i;
	}
	
	public static boolean bfs(SiNode root, SiNode dest){
		ArrayList<SiNode> fringe = new ArrayList<SiNode>();
		fringe.add(root);
		boolean continuel=true;
		while ((!fringe.isEmpty())&&(continuel)){
			maxFringe= Math.max(maxFringe,fringe.size());
			SiNode toExp=fringe.remove(0);
			noExp++;
			ArrayList<SiNode> pending= toExp.expand();
			for(int k=0; k< pending.size(); k++){
				//System.out.println(pending.get(k).getID()+" "+pending.get(k).getDepth());
				if (pending.get(k).getID()==dest.getID()){
					System.out.println("Depth= "+pending.get(k).getDepth());
					System.out.println("FOUND!!!"+dest.getID());
					continuel= false;
					break;
				}
				else{
					fringe.add(pending.get(k));
				}
			}
		}
		System.out.println("No. Nodes Expanded= "+noExp);
		System.out.println("Max. Fringe Size= "+maxFringe);
		return true;
	}
	
	public static boolean dfs(SiNode root, SiNode dest, int limit){
		ArrayList<SiNode> fringe = new ArrayList<SiNode>();
		fringe.add(root);
		boolean continuel=true;
		while ((!fringe.isEmpty())&&(continuel)){
			maxFringe= Math.max(maxFringe,fringe.size());
			SiNode toExp=fringe.remove(0);
			noExp++;
			ArrayList<SiNode> pending= toExp.expand();
			Collections.shuffle(pending);
			for(int k=0; k< pending.size(); k++){
				//System.out.println(pending.get(k).getID()+" "+pending.get(k).getDepth());
				if (pending.get(k).getID()==dest.getID()){
					System.out.println("FOUND!!!"+pending.get(k).getDepth());
					continuel= false;
					System.out.println("No. Nodes Expanded= "+noExp);
					System.out.println("Fringe Size= "+maxFringe);
					return true;
				}
				else{
					if(pending.get(k).getDepth()<limit){
						fringe.add(0, pending.get(k));
					}
				}
			}
		}
		return false;
	}

	public static boolean astar(SiNode root, SiNode dest){
		PriorityQueueComparator pqc= new PriorityQueueComparator(tiles,dest);
		PriorityQueue<SiNode> fringe = new PriorityQueue<SiNode>(1,pqc);
		fringe.add(root);
		boolean continuel=true;
		while ((!fringe.isEmpty())&&(continuel)){
			maxFringe= Math.max(maxFringe,fringe.size());
			SiNode toExp=fringe.poll();
			noExp++;
			ArrayList<SiNode> pending= toExp.expand();
			for(int k=0; k< pending.size(); k++){
				//System.out.println(pending.get(k).getID()+" "+pending.get(k).getDepth());
				if (pending.get(k).getID()==dest.getID()){
					System.out.println("FOUND!!!"+dest.getDepth());
					continuel= false;
					break;
				}
				else{
					fringe.add(pending.get(k));
				}
			}
		}
		System.out.println("No. Nodes Expanded= "+noExp);
		System.out.println("Max. Fringe Size= "+maxFringe);
		return true;
	}
	
	public static ArrayList<SiNode> getPath(SiNode start, SiNode root){
		SiNode end= start;
		ArrayList<SiNode> path= new ArrayList<SiNode>();
		path.add(start);
		while (end.getID()!=root.getID()){
			end=end.getParent();
			path.add(end);
		}
		return path;
	}
	
	public static void printPath(ArrayList<SiNode> path){
		for (int i=path.size()-1;i>=0;i--){
			System.out.println(path.get(i).getID());
		}
	}	
}