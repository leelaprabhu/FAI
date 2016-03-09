import java.util.ArrayList;

public class Stick{
	private int stickID;
	private ArrayList<Integer> discIDs= new ArrayList<Integer>(); 
	Disc top= null;
	
	public Stick(int stickID){
		this.stickID= stickID;
	}
	
	public Disc getTop(){
		return top;
	}
	
	public boolean putTop(Disc newDisc){
		if (top==null){
			top=newDisc;
			discIDs.add(newDisc.getID());
			return true;
		}
		else{
			if(newDisc.getID()<top.getID()){
				top=newDisc;
				discIDs.add(newDisc.getID());
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public ArrayList<Integer> getDiscs(){
		return discIDs;
	}
}