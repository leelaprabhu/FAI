import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SiNode{
	//private State state1;
	private SiNode up;
	private SiNode down;
	private SiNode right;
	private SiNode left;
	private SiNode parent= null;
	private String tiles[];
	private long uniqueID;
	private int size;
	private World world;
	private int depth=0;

	public SiNode(HashMap<String,int[]> state, World world){
		//this.state=state;
		this.world=world;
		tiles= world.getTiles();
		size= world.getSize();
		/*this.uniqueID= (int) (state.get("A")[0]+state.get("A")[1]*4+
		state.get("B")[0]*Math.pow(4.0, 2.0)+state.get("B")[1]*Math.pow(4.0, 3.0)+
		state.get("C")[0]*Math.pow(4.0, 4.0)+state.get("C")[1]*Math.pow(4.0, 5.0)+
		state.get("X")[0]*Math.pow(4.0, 6.0)+state.get("X")[1]*Math.pow(4.0, 7.0));*/
		this.uniqueID= (int) (state.get("X")[0]+state.get("X")[1]*4
				+state.get("A")[0]*Math.pow(4.0, 2.0)+state.get("A")[1]*Math.pow(4.0, 3.0)
				+state.get("B")[0]*Math.pow(4.0, 4.0)+state.get("B")[1]*Math.pow(4.0, 5.0)
				+state.get("C")[0]*Math.pow(4.0, 6.0)+state.get("C")[1]*Math.pow(4.0, 7.0)
				+state.get("D")[0]*Math.pow(4.0, 8.0)+state.get("D")[1]*Math.pow(4.0, 9.0)
				);
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
	
	public ArrayList<SiNode> expand(){
		ArrayList<SiNode> expansion= new ArrayList<SiNode>();
		this.up= this.moveUp();
		this.down= this.moveDown();
		this.right= this.moveRight();
		this.left= this.moveLeft();
		ArrayList<SiNode> dir= new ArrayList<SiNode>();
		dir.add(up);
		dir.add(down);
		dir.add(right);
		dir.add(left);
		for(int i=0; i<dir.size(); i++){
			if (dir.get(i).getID()!= this.getID()){
				if(this.getParent()!=null){
					if(dir.get(i).getID()!= this.getParent().getID()){
						expansion.add(dir.get(i));
						dir.get(i).setParent(this);
						dir.get(i).setDepth(depth+1);
						}
				}
				else{
					expansion.add(dir.get(i));
					dir.get(i).setParent(this);
					dir.get(i).setDepth(depth+1);
				}
			}
		}
		return expansion;
	}
	

	public SiNode moveUp(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(((uniqueID>>0*2)&(3))<(size-1)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*2)&(3));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*2)&(3));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) (((uniqueID>>0*2)&(3))+1);
			a[0][1]=(int) ((uniqueID>>1*2)&(3));
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][0]=a[i][0]-1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveDown(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(((uniqueID>>0*2)&(3))>0){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*2)&(3));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*2)&(3));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) (((uniqueID>>0*2)&(3))-1);
			a[0][1]=(int) ((uniqueID>>1*2)&(3));
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][0]=a[i][0]+1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveRight(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(((uniqueID>>1*2)&(3))<(size-1)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*2)&(3));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*2)&(3));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) ((uniqueID>>0*2)&(3));
			a[0][1]=(int) (((uniqueID>>1*2)&(3))+1);
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][1]=a[i][1]-1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveLeft(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(((uniqueID>>1*2)&(3))>0){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*2)&(3));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*2)&(3));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) ((uniqueID>>0*2)&(3));
			a[0][1]=(int) (((uniqueID>>1*2)&(3))-1);
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][1]=a[i][1]+1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public void display(){
		//char print[][]={{' ',' ',' ',' '},{' ',' ',' ',' '},{' ',' ',' ',' '},{' ',' ',' ',' '}};
		char print[][]={{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '}};
		print[(int) ((uniqueID>>0*2)&(3))][(int) ((uniqueID>>1*2)&(3))]='X';
		print[(int) ((uniqueID>>2*2)&(3))][(int) ((uniqueID>>3*2)&(3))]='A';
		print[(int) ((uniqueID>>4*2)&(3))][(int) ((uniqueID>>5*2)&(3))]='B';
		print[(int) ((uniqueID>>6*2)&(3))][(int) ((uniqueID>>7*2)&(3))]='C';
		//print[(int) ((uniqueID>>8*2)&(3))][(int) ((uniqueID>>9*2)&(3))]='D';
		for(int i=size-1; i>=0; i--){
			System.out.print("|");
			for(int j=0; j<size; j++){
				System.out.print(print[i][j]+"|");
			}
			System.out.println();
		}
		//System.out.println("["+((uniqueID>>0*2)&(3))+","+((uniqueID>>1*2)&(3))+"] ["+((uniqueID>>2*2)&(3))+","+((uniqueID>>3*2)&(3))+"] ["+((uniqueID>>4*2)&(3))+","+((uniqueID>>5*2)&(3))+"] ["+((uniqueID>>6*2)&(3))+","+((uniqueID>>7*2)&(3))+"]");
	}
	
	public long getID(){
		return uniqueID;
	}
	
	public SiNode getUp(){
		return up;
	}
	
	public SiNode getDown(){
		return down;
	}

	public SiNode getRight(){
		return right;
	}

	public SiNode getLeft(){
		return left;
	}
	
	public void setParent(SiNode parent){
		this.parent=parent;
	}
	
	public SiNode getParent(){
		return parent;
	}
	
}

