package logic;
import java.util.ArrayList;

import betza.Betza;
import view.Position;

public class Piece {
	public String name;
	public String betzaNotation;
	public ArrayList<Position> moveList = new ArrayList<Position>();
	
	public Piece(String name, String betzaNotation) {
		this.name = name;
		this.betzaNotation = betzaNotation;
		this.moveList = Betza.parseBetza(betzaNotation);
	}
	
	public String getName() {
		return name;
	}
	
	public String getBetzaNotation() {
		return betzaNotation;
	}
	
	public ArrayList<Position> getMoveList(){
		return moveList;
	}
	
	
}
