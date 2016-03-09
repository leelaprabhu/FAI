import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Node{
	private long uniqueID;
	private int[] state;
	private int noDisc=9;
	private int noStick=4;
	private int[] stateStick= new int[4];
	
	private Node parent= null;
	private int depth=0;
	
	public Node(int[] state){
		this.state=state;
		this.uniqueID= calcID(state);
		this.stateStick=calcStateStick(state);
	}
	
	public Node(long uniqueID){
		this.uniqueID= uniqueID;
		this.state= calcState(uniqueID);
		this.stateStick=calcStateStick(state);
	}

	public void setParent(Node parent){
		this.parent=parent;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public int[] getStick(){
		return stateStick;
	}
	
	private long calcID(int[] state) {
		long id=0;
		for(int i=0; i<noDisc; i++){
			id=(long)(id+(state[i]*Math.pow(4.0, i)));
		}
		return id;
	}
	
	private int[] calcState(long uniqueID) {
		int state[]= new int[noDisc];
		for(int i=0; i<noDisc; i++){
			state[i]=(int)((uniqueID>>i*2)&(3));
		}
		return state;
	}
	
	private int[] calcStateStick(int[] state){
		int stick[]= new int[]{0,0,0,0};
		for (int i=0; i<state.length; i++){
			stick[state[i]]++;
		}
		return stick;
	}
	
	public long getID(){
		return uniqueID;
	}
	
	public int[] getState(){
		return state;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
	
	public Node transferDisc(int disc, int stickTo){
		int count1=0;
		for(int i=0; i<disc; i++){
			if (state[i]==state[disc]){
				count1++;
			}
		}
		if(count1>0)
			return this;
		
		int count2=0;
		for(int i=0; i<disc; i++){
			if (state[i]==stickTo){
				count2++;
			}
		}
		if(count2>0)
			return this;
		
		int[] newState = new int[noDisc];
		for (int i=0; i<noDisc; i++){
			newState[i]= state[i];
		}
		newState[disc]=stickTo;
		Node newNode= new Node(newState);
		return newNode;
	}

	public ArrayList<Node> expand(HashMap<Long, Node> track){
		ArrayList<Node> expansion= new ArrayList<Node>();
		ArrayList<Node> dir= new ArrayList<Node>();

		for(int i=0; i<noDisc; i++){
			for (int j=0; j<noStick; j++){
				dir.add(transferDisc(i,j));
			}
		}
		
		for(int h=0; h<dir.size(); h++){
			Node node=dir.get(h);
			if (!track.containsKey(node.getID())){
				track.put(node.getID(), node);
				node.setParent(this);
				expansion.add(node);
				node.setDepth(depth+1);
			}
			else{
				if((depth+1)<track.get(node.getID()).getDepth()){
					track.get(node.getID()).setParent(this);
					expansion.add(track.get(node.getID()));
					track.get(node.getID()).setDepth(depth+1);
				}
			}
		}
		return expansion;
	}
		
	public void display() throws IOException{
		for(int i=0; i<noDisc; i++){
			File log= new File("log.txt");
			FileWriter fileWriter = new FileWriter(log, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(state[i]+",");
			System.out.print(state[i]+",");
			bufferedWriter.close();
		}
		for(int i=0; i<noStick; i++){
			File log= new File("log.txt");
			FileWriter fileWriter = new FileWriter(log, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(stateStick[i]+",");
			System.out.print(stateStick[i]+",");
			bufferedWriter.close();
		}
		
	}
}