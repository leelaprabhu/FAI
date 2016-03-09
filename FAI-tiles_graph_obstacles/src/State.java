import java.io.*;

public class State{
	private int[] A;
	private int[] B;
	private int[] C;
	private int[] X;
	
	public State(int[] A, int[] B, int[] C, int[] X){
		this.A=A;
		this.B=B;
		this.C=C;
		this.X=X;
	}
	
	public State(){
	}
	
	public int[] getA(){
		return A;
	}
	
	public int[] getB(){
		return B;
	}
	
	public int[] getC(){
		return C;
	}
	
	public int[] getX(){
		return X;
	}
	
	public void setA(int[] A){
		this.A= A;
	}
	
	public void setB(int[] B){
		this.B= B;
	}
	
	public void setC(int[] C){
		this.C= C;
	}
	
	public void setX(int[] X){
		this.X= X;
	}
	
	public void display(){
		System.out.println("["+A[0]+","+A[1]+"] ["+B[0]+","+B[1]+"] ["+C[0]+","+C[1]+"] ["+X[0]+","+X[1]+"]");
	}
}