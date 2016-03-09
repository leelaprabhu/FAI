public class AstarNode {

	private SiNode node;
	private int depth;
	public AstarNode(SiNode node, int depth) {
		this.node=node;
		this.depth= depth;
	}
	
	public SiNode getNode(){
		return node;
	}
	
	public int getDepth(){
		return depth;
	}

}
