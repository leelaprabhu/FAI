
public class Disc {
	private int discID;
	private Stick onStick;
	public Disc(int discID){
		this.discID=discID;
	}
	
	public void moveTo(Stick stickID){
		this.onStick= onStick;
	}
	
	public Stick getStick(){
		return onStick;
	}
	
	public int getID(){
		return discID;
	}
}
