import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Queue;

public class Start{
	static String tiles[]={"A","B","C"};
	static int size=4;
	static int noExp=0;
	static int maxFringe=1;
	public static void main(String args[]) throws IOException{
		World world = new World(tiles, size);
		System.out.println("Start");
		int[] A={0,0};
		int[] B={0,1};
		int[] C={0,2};
		int[] O={2,2};
		int[] X={0,3};
		HashMap<String,int[]> start= new HashMap<String,int[]>();
		HashMap<Long, SiNode> track= new HashMap<Long, SiNode>();
		start.put("X", X);
		start.put("A", A);
		start.put("B", B);
		start.put("C", C);
		start.put("O", O);
		
		int[] Ag={2,1};
		int[] Bg={1,1};
		int[] Cg={0,1};
		int[] Og=O;
		int[] Xg={0,3};
		HashMap<String,int[]> goal= new HashMap<String,int[]>();
		goal.put("X", Xg);
		goal.put("A", Ag);
		goal.put("B", Bg);
		goal.put("C", Cg);
		goal.put("O", Og);
		
		SiNode root =new SiNode(start, world);
		SiNode dest =new SiNode(goal, world);
		root.display();
		root.moveUp().moveUp().moveUp().moveLeft().moveDown().display();
		//System.out.println((dest.getID()>>8*3)&(7));
		//System.out.println("!!!!!!!!!!"+root.getID());
	
		//bfs(root,dest);
		//idfs(dest,root);
		//eidfs(dest,root);
		//astar(root,dest);
		//dfs(root,dest,18);
		dfs(root,dest,10000);
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
	
	public static int eidfs(SiNode dest, SiNode root){
		boolean isFound=false;
		int i=1;
		while (!isFound){
			i=i*2;
			isFound= dfs(root,dest,i);
		}
		return i;
	}
	
	public static boolean dfs(SiNode root, SiNode dest, int limit){
		HashMap<Long, SiNode> track= new HashMap<Long, SiNode>();
		track.put(root.getID(), root);
		int depth=0;
		Stack<SiNode> fringe = new Stack<SiNode>();
		fringe.push(root);
		while((!track.containsKey(dest.getID()))&&(!fringe.isEmpty())){
			SiNode toExp= fringe.pop();
			noExp++;
			if(track.get(toExp.getID()).getDepth()<limit){
				ArrayList<SiNode> pending= toExp.expand(track);
				if(track.containsKey(dest.getID())){
					dest.setParent(toExp);
					System.out.println("FOUND! "+depth);
					break;
				}
				for(int k=0; k<pending.size(); k++){
					fringe.push(pending.get(k));
				}
			}
			maxFringe= Math.max(maxFringe, fringe.size());
		}
		
		if(track.get(dest.getID())!=null){
			System.out.println("Mem Complexity: "+maxFringe);
			System.out.println("Time Complexity: "+noExp);
			System.out.println("No Nodes Checked: "+track.size());
			System.out.println("Depth: "+track.get(dest.getID()).getDepth());
			return true;
		}
		else{
			//System.out.println("ExpSize "+maxFringe);
			//System.out.println(noExp);
			return false;
		}
		
	}
	
	public static boolean bfs(SiNode root, SiNode dest){
		HashMap<Long, SiNode> track= new HashMap<Long, SiNode>();
		track.put(root.getID(), root);
		int depth=0;
		ArrayList<SiNode> fringe = new ArrayList<SiNode>();
		fringe.add(root);
		int maxFringe=1;
		while((!track.containsKey(dest.getID()))&&(!fringe.isEmpty())){
			SiNode toExp= fringe.remove(0);
			noExp++;
			ArrayList<SiNode> pending= toExp.expand(track);
			if(track.containsKey(dest.getID())){
				dest.setParent(toExp);
				System.out.println("FOUND! "+depth);
				break;
			}
			for(int k=0; k<pending.size(); k++){
				fringe.add(pending.get(k));
			}
			maxFringe= Math.max(maxFringe, fringe.size());
		}
		
		if(track.get(dest.getID())!=null){
			System.out.println("Mem Complex: "+maxFringe);
			System.out.println("Time Complex: "+noExp);
			System.out.println("No Nodes Checked: "+track.size());
			System.out.println("Depth: "+track.get(dest.getID()).getDepth());
			return true;
		}
		else{
			//System.out.println("ExpSize "+maxFringe);
			//System.out.println(noExp);
			return false;
		}
		
	}
	
	public static void astar(SiNode root, SiNode dest){
		HashMap<Long, SiNode> track= new HashMap<Long, SiNode>();
		track.put(root.getID(), root);
		int depth=0;
		PriorityQueueComparator pqc= new PriorityQueueComparator(tiles,dest);
		PriorityQueue<SiNode> pq= new PriorityQueue<SiNode>(1,pqc);
		pq.add(root);
		while(!track.containsKey(dest.getID())){
			noExp++;
			SiNode toExp= pq.poll();
			ArrayList<SiNode> pending= toExp.expand(track);
			if(track.containsKey(dest.getID())){
				dest.setParent(toExp);
				System.out.println("FOUND! "+depth);
				break;
			}
			pq.addAll(pending);
		}
		System.out.println("Mem Complexity: "+pq.size());
		System.out.println("Time Complexity: "+noExp);
		System.out.println("No Nodes Checked: "+track.size());
		System.out.println("Depth: "+track.get(dest.getID()).getDepth());
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