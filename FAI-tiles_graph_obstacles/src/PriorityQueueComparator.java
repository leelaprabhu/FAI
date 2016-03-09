import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<SiNode>{
	private String tiles[];
	private SiNode dest;
	
	public PriorityQueueComparator(String[] tiles, SiNode dest){
		this.tiles=tiles;
		this.dest=dest;
	}
	
	public int compare(SiNode a, SiNode b) {
		//int costA= heuristicManhattan(a,dest)+a.getDepth();
		//int costB= heuristicManhattan(b,dest)+b.getDepth();
		int costA= heuristicIncorrect(a,dest)+a.getDepth();
		int costB= heuristicIncorrect(b,dest)+b.getDepth();
		if(costA<costB)
			return -1;
		else if(costA>costB)
			return 1;
		else
			return 0;
	}
	
	public int heuristicIncorrect(SiNode check, SiNode dest){
		int cost=4;
		for(int i=0; i<=tiles.length; i++){
			if ((((check.getID()>>(2*i)*2)&(3))==((dest.getID()>>(2*i)*2)&(3)))&&
				(((check.getID()>>(2*i+1)*2)&(3))==((dest.getID()>>(2*i+1)*2)&(3)))){ //check if A is in the correct place
				cost--;
			}
		}
		return cost;
	}
	
	public int heuristicManhattan(SiNode check, SiNode dest){
		int cost=0;
		for(int i=0; i<=tiles.length; i++){
			cost=cost+Math.abs((int)((check.getID()>>(2*i)*2)&(3))-(int)((dest.getID()>>(2*i)*2)&(3)))+
			Math.abs((int)((check.getID()>>(2*i+1)*2)&(3))-(int)((dest.getID()>>(2*i+1)*2)&(3)));
		}
		return cost;
	}

}
