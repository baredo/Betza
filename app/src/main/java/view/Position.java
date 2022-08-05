package view;
import utils.Constants;

public class Position {
	public int x;
	public int y;
	public int type;
	public int captureFlag = Constants.CAPTURE_BOTH;
	
	public Position(){

	}
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public Position(int[] pos, int type){
		this.x = pos[0];
		this.y = pos[1];
		this.type = type;
	}
	
	public Position(int[] pos, int type, Position flagpositionPrototype){
		this.x = pos[0];
		this.y = pos[1];
		this.type = type;
		
		setFlags(flagpositionPrototype);
	}
	
	public void setFlags(Position flagpositionPrototype) {
		this.captureFlag = flagpositionPrototype.captureFlag;
	}
}
