public class World {
	private String tiles[];
	private int sizeX;
	private int sizeY;
	private int size;
	
	public World(String[] tiles, int size){
		this.tiles=tiles;
		this.size=size;
	}
	
	public World(String[] tiles, int sizeX, int sizeY){
		this.tiles=tiles;
		this.sizeX=sizeX;
		this.sizeY=sizeY;
	}
	
	public String[] getTiles(){
		return tiles;
	}
	
	public int getSizeX(){
		return sizeX;
	}
	
	public int getSizeY(){
		return sizeY;
	}
	
	public int getSize(){
		return size;
	}
	
}
