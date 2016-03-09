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
		this.uniqueID= (int) (state.get("X")[0]+state.get("X")[1]*8+
				state.get("A")[0]*Math.pow(8.0, 2.0)+state.get("A")[1]*Math.pow(8.0, 3.0)
				+state.get("B")[0]*Math.pow(8.0, 4.0)+state.get("B")[1]*Math.pow(8.0, 5.0)
				+state.get("C")[0]*Math.pow(8.0, 6.0)+state.get("C")[1]*Math.pow(8.0, 7.0)
				+state.get("O")[0]*Math.pow(8.0, 8.0)+state.get("O")[1]*Math.pow(8.0, 9.0)
				);
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
	
	public ArrayList<SiNode> expand(HashMap<Long, SiNode> track){
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
		for(int h=0; h<dir.size(); h++){
			SiNode node=dir.get(h);
			//if(node.getID()==55360)
			//	System.out.println("====="+depth+" "+node.getDepth()+" "+node);
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

	public SiNode moveUp(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+2][2];
		boolean noClash= ((((uniqueID>>0*3)&(7))+1)!=((uniqueID>>8*3)&(7)))||(((uniqueID>>1*3)&(7))!=((uniqueID>>9*3)&(7)));
		System.out.println(noClash);
		if((((uniqueID>>0*3)&(7))<(size-1))&&(noClash)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*3)&(7));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*3)&(7));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) (((uniqueID>>0*3)&(7))+1);
			a[0][1]=(int) ((uniqueID>>1*3)&(7));
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][0]=a[i][0]-1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			a[tiles.length+1][0]=(int) ((uniqueID>>8*3)&(7));
			a[tiles.length+1][1]=(int) ((uniqueID>>9*3)&(7));
			
			temp.put("O", a[tiles.length+1]);
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveDown(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+2][2];
		boolean noClash= ((((uniqueID>>0*3)&(7))-1)!=((uniqueID>>8*3)&(7)))||(((uniqueID>>1*3)&(7))!=((uniqueID>>9*3)&(7)));
		if((((uniqueID>>0*3)&(7))>0)&&(noClash)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*3)&(7));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*3)&(7));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) (((uniqueID>>0*3)&(7))-1);
			a[0][1]=(int) ((uniqueID>>1*3)&(7));
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][0]=a[i][0]+1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			a[tiles.length+1][0]=(int) ((uniqueID>>8*3)&(7));
			a[tiles.length+1][1]=(int) ((uniqueID>>9*3)&(7));
			
			temp.put("O", a[tiles.length+1]);
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveRight(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+2][2];
		boolean noClash= (((uniqueID>>0*3)&(7))!=((uniqueID>>8*3)&(7)))||((((uniqueID>>1*3)&(7))+1)!=((uniqueID>>9*3)&(7)));
		if((((uniqueID>>1*3)&(7))<(size-1))&&(noClash)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*3)&(7));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*3)&(7));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) ((uniqueID>>0*3)&(7));
			a[0][1]=(int) (((uniqueID>>1*3)&(7))+1);
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][1]=a[i][1]-1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			a[tiles.length+1][0]=(int) ((uniqueID>>8*3)&(7));
			a[tiles.length+1][1]=(int) ((uniqueID>>9*3)&(7));
			
			temp.put("O", a[tiles.length+1]);
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public SiNode moveLeft(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+2][2];
		boolean noClash= (((uniqueID>>0*3)&(7))!=((uniqueID>>8*3)&(7)))||((((uniqueID>>1*3)&(7))-1)!=((uniqueID>>9*3)&(7)));
		if((((uniqueID>>1*3)&(7))>0)&&(noClash)){
			for (int i=1; i<=tiles.length; i++)
			{
				a[i][0]=(int) ((uniqueID>>(2*i)*3)&(7));
				a[i][1]=(int) ((uniqueID>>(2*i+1)*3)&(7));
				temp.put(tiles[i-1], a[i]);
			}
			
			a[0][0]=(int) ((uniqueID>>0*3)&(7));
			a[0][1]=(int) (((uniqueID>>1*3)&(7))-1);
			temp.put("X", a[0]);
			
			for (int i=1; i<=tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i-1]))){
					a[i][1]=a[i][1]+1;
					temp.put(tiles[i-1], a[i]);
				}
			}
			a[tiles.length+1][0]=(int) ((uniqueID>>8*3)&(7));
			a[tiles.length+1][1]=(int) ((uniqueID>>9*3)&(7));
			
			temp.put("O", a[tiles.length+1]);
			SiNode newNode =new SiNode(temp, world);
			return newNode;
		}
		else
			return this;
	}
	
	public void display(){
		//char print[][]={{' ',' ',' ',' '},{' ',' ',' ',' '},{' ',' ',' ',' '},{' ',' ',' ',' '}};
		char print[][]={{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '},{' ',' ',' ',' ',' '}};
		print[(int) ((uniqueID>>0*3)&(7))][(int) ((uniqueID>>1*3)&(7))]='X';
		print[(int) ((uniqueID>>2*3)&(7))][(int) ((uniqueID>>3*3)&(7))]='A';
		print[(int) ((uniqueID>>4*3)&(7))][(int) ((uniqueID>>5*3)&(7))]='B';
		print[(int) ((uniqueID>>6*3)&(7))][(int) ((uniqueID>>7*3)&(7))]='C';
		print[(int) ((uniqueID>>8*3)&(7))][(int) ((uniqueID>>9*3)&(7))]='O';
		for(int i=size-1; i>=0; i--){
			System.out.print("|");
			for(int j=0; j<size; j++){
				System.out.print(print[i][j]+"|");
			}
			System.out.println();
		}
		//System.out.println("["+((uniqueID>>0*2))+","+((uniqueID>>1*2)&(3))+"] ["+((uniqueID>>2*2)&(3))+","+((uniqueID>>3*2)&(3))+"] ["+((uniqueID>>4*2)&(3))+","+((uniqueID>>5*2)&(3))+"] ["+((uniqueID>>6*2)&(3))+","+((uniqueID>>7*2)&(3))+"]");
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

