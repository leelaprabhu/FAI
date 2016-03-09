import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Node{
	//private State state1;
	private Node up;
	private Node down;
	private Node right;
	private Node left;
	private HashMap<String,int[]> state= new HashMap<String,int[]>();
	private String tiles[]={"A","B","C"};
	private int uniqueID;
	
	public Node(HashMap<String,int[]> state){
		this.state=state;
		this.uniqueID= (int) (state.get("A")[0]+state.get("A")[1]*4+
		state.get("B")[0]*Math.pow(4.0, 2.0)+state.get("B")[1]*Math.pow(4.0, 3.0)+
		state.get("C")[0]*Math.pow(4.0, 4.0)+state.get("C")[1]*Math.pow(4.0, 5.0)+
		state.get("X")[0]*Math.pow(4.0, 6.0)+state.get("X")[1]*Math.pow(4.0, 7.0));
	}
	
	public void expand(){
		this.up= this.moveUp();
		this.down= this.moveDown();
		this.right= this.moveRight();
		this.left= this.moveLeft();
		
	}

	public Node moveUp(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(state.get("X")[0]<3){
			for (int i=0; i<tiles.length; i++)
			{
				a[i][0]=state.get(tiles[i])[0];
				a[i][1]=state.get(tiles[i])[1];
				temp.put(tiles[i], a[i]);
			}
			
			a[3][0]=state.get("X")[0]+1;
			a[3][1]=state.get("X")[1];
			temp.put("X", a[3]);
			
			for (int i=0; i<tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i]))){
					a[i][0]=a[i][0]-1;
					temp.put(tiles[i], a[i]);
				}
			}
			
			Node newNode =new Node(temp);
			return newNode;
		}
		else
			return this;
	}
	
	public Node moveDown(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(state.get("X")[0]>0){
			for (int i=0; i<tiles.length; i++)
			{
				a[i][0]=state.get(tiles[i])[0];
				a[i][1]=state.get(tiles[i])[1];
				temp.put(tiles[i], a[i]);
			}
			
			a[3][0]=state.get("X")[0]-1;
			a[3][1]=state.get("X")[1];
			temp.put("X", a[3]);
			
			for (int i=0; i<tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i]))){
					a[i][0]=a[i][0]+1;
					temp.put(tiles[i], a[i]);
				}
			}
			
			Node newNode =new Node(temp);
			return newNode;
		}
		else
			return this;
	}
	
	public Node moveRight(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(state.get("X")[1]<3){
			for (int i=0; i<tiles.length; i++)
			{
				a[i][0]=state.get(tiles[i])[0];
				a[i][1]=state.get(tiles[i])[1];
				temp.put(tiles[i], a[i]);
			}
			
			a[3][0]=state.get("X")[0];
			a[3][1]=state.get("X")[1]+1;
			temp.put("X", a[3]);
			
			for (int i=0; i<tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i]))){
					a[i][1]=a[i][1]-1;
					temp.put(tiles[i], a[i]);
				}
			}
			
			Node newNode =new Node(temp);
			return newNode;
		}
		else
			return this;
	}
	
	public Node moveLeft(){
		HashMap<String,int[]> temp= new HashMap<String,int[]>();
		int a[][]=new int[tiles.length+1][2];
		if(state.get("X")[1]>0){
			for (int i=0; i<tiles.length; i++)
			{
				a[i][0]=state.get(tiles[i])[0];
				a[i][1]=state.get(tiles[i])[1];
				temp.put(tiles[i], a[i]);
			}
			
			a[3][0]=state.get("X")[0];
			a[3][1]=state.get("X")[1]-1;
			temp.put("X", a[3]);
			
			for (int i=0; i<tiles.length; i++){
				if (Arrays.equals(temp.get("X"),temp.get(tiles[i]))){
					a[i][1]=a[i][1]+1;
					temp.put(tiles[i], a[i]);
				}
			}
			
			Node newNode =new Node(temp);
			return newNode;
		}
		else
			return this;
	}
	
	public void display(){
		System.out.println("["+state.get("A")[0]+","+state.get("A")[1]+"] ["+state.get("B")[0]+","+state.get("B")[1]+"] ["+state.get("C")[0]+","+state.get("C")[1]+"] ["+state.get("X")[0]+","+state.get("X")[1]+"]");
	}
	
	public int getID(){
		return uniqueID;
	}
	
	public Node getUp(){
		return up;
	}
	
	public Node getDown(){
		return down;
	}

	public Node getRight(){
		return right;
	}

	public Node getLeft(){
		return left;
	}

	
	
}

