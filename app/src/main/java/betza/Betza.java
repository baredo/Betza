package betza;
import java.util.ArrayList;

import utils.Constants;
import utils.Notation;
import view.Position;

public class Betza {
	
	public static ArrayList<Position> parseBetza(String moves) {
		ArrayList<BetzaMove> moveList = compile(moves);
		if(moveList == null) return null;
		return  addMove(moveList);
	}
	
	private static ArrayList<BetzaMove> compile(String moves) {
		if (moves.isEmpty()) return null;
		
		ArrayList<BetzaMove> moveList = new ArrayList<BetzaMove>();
		BetzaMove move;
		char c = 0;
		int i = 0;
		
		//Transformamos los atomos de ajedrez a atomos base
		moves = moves.replaceAll("B", "F0");
		moves = moves.replaceAll("R", "W0");
		moves = moves.replaceAll("Q", "W0F0");
		moves = moves.replaceAll("K", "WF");

		while (i < moves.length()) {
			move = new BetzaMove();
			// Recogemos los prefijos hasta llegar al movimiento base
			for (; i < moves.length(); i++) {
				c = moves.charAt(i);
				if (isBasic(c)) { move.atom += c; break; } 
				else move.prefix += c;
			}
			i++;
			//Si tenemos un deslizamiento infinito en formato viejo
			//ejem: WW en vez de W0, lo transformamos
			if(i < moves.length() && moves.substring(i-1, i).equals(moves.substring(i, i+1))) {
				moves = moves.substring(0, i)+"0"+moves.substring(i+1);
			}
			
			// Recogemos los sufijos hasta que no haya mas
			for (; i < moves.length(); i++) {
				c = moves.charAt(i);
				if (isSufix(c)) move.suffix += c;
				else break;
			}
			moveList.add(move);
		}
		return moveList;
	}
	
	private static ArrayList<Position> addMove(ArrayList<BetzaMove> moveList) {
		ArrayList<Position> positionList = new ArrayList<Position>();
		Position flagPositionPrototype;
		int [][] atomMoves = null;
		int type = -1;
		ArrayList<Position> bufferPositionList = new ArrayList<Position>();
		for(BetzaMove move : moveList) {
			
			if(move.atom == 'W') atomMoves = Notation.getPosW();
			if(move.atom == 'F') atomMoves = Notation.getPosF();
			if(move.atom == 'N') atomMoves = Notation.getPosN();
			if(move.atom == 'A') atomMoves = Notation.getPosA();
			if(move.atom == 'D') atomMoves = Notation.getPosD();
			if(move.atom == 'G') atomMoves = Notation.getPosG();
			if(move.atom == 'Z') atomMoves = Notation.getPosZ();
			if(move.atom == 'C') atomMoves = Notation.getPosC();
			if(move.atom == 'H') atomMoves = Notation.getPosH();
			
			type = getMoveType(move);
			
			//set prefix flag
			flagPositionPrototype = setFlagPosition(move);
			
			//add temporal atom moves (without filter with direction)
			for(int[] pos : atomMoves) {
				if(!positionExist(bufferPositionList, pos) && !positionExist(positionList, pos)) 
					bufferPositionList.add(new Position(pos,type,flagPositionPrototype));		
			}
			
			//filter atom by direction
			if(move.prefix.length() > 0) { 
				bufferPositionList= applyPrefix(move.prefix, bufferPositionList);
			}
			
			//add additional atoms by range
			bufferPositionList = rangeModifier(move.suffix, bufferPositionList,type);
			for(Position pos : bufferPositionList) {
				positionList.add(pos);
			}
			bufferPositionList.clear();
			
		}
		return positionList;
	}
	
	private static boolean isBasic(char c) {
		for(char basic : Notation.getBasic()) {
			if(c == basic) return true;			
		}
		return false;
	}
	
	private static boolean isSufix(char c) {
		for(char suffix : Notation.getSuffix()) {
			if(c == suffix) return true;			
		}
		return false;
	}
	
	private static void printMove(BetzaMove move) {
		System.out.println(move.prefix + "-" + move.atom + "-" + move.suffix);
	}
	
	private static ArrayList<Position> rangeModifier(String suffixRange, ArrayList<Position> positionList, int type){
		if (suffixRange.length() > 0 && !suffixRange.matches("0")) {
			ArrayList<Position> bufferPositionList = new ArrayList<Position>();
			int range = Integer.parseInt(suffixRange);
			for(Position pos : positionList) {
				for(int i = 2; i <= range; i++) {
					bufferPositionList.add(new Position(pos.x*i,pos.y*i,type));
				}

				if(range == 0) 
					bufferPositionList.add(new Position(pos.x,pos.y,type));
			}
			for(Position pos : bufferPositionList) {
				positionList.add(pos);
			}
		}
		return positionList;
	}
	private static ArrayList<Position> directionFilter(String direccion, ArrayList<Position> positionList) {
		ArrayList<Position> positionListForRemove = new ArrayList<Position>();
		
		if(direccion.indexOf('f') >= 0) {
			if(direccion.indexOf("ff") >= 0) {
				for(Position pos : positionList) {
					if(pos.y <= 1) positionListForRemove.add(pos);
				}
			} else {
				for(Position pos : positionList) {
					if(pos.y <= 0) positionListForRemove.add(pos);
				}
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		if(direccion.indexOf('b') >= 0) {
			for(Position pos : positionList) {
				if(pos.y >= 0) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
				
			}
			positionListForRemove.clear();
		}
		
		if(direccion.indexOf('r') >= 0) {
			for(Position pos : positionList) {
				if(pos.x <= 0) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		if(direccion.indexOf('l') >= 0) {
			for(Position pos : positionList) {
				if(pos.x >= 0) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		if(direccion.indexOf('v') >= 0) {
			for(Position pos : positionList) {
				if(Math.abs(pos.x) > Math.abs(pos.y)) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		if(direccion.indexOf('s') >= 0) {
			for(Position pos : positionList) {
				if(Math.abs(pos.x) < Math.abs(pos.y)) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		//other type of prefix
		if(direccion.indexOf('c') == Constants.CAPTURE_YES) {
			for(Position pos : positionList) {
				if(Math.abs(pos.x) < Math.abs(pos.y)) positionListForRemove.add(pos);
			}
			for(Position pos : positionListForRemove) {
				positionList.remove(pos);
			}
			positionListForRemove.clear();
		}
		
		return positionList;	
	}
	
	private static ArrayList<Position> applyPrefix(String prefix, ArrayList<Position> positionListReference) {
		//Tenemos que aplicar el prefijo al atomo, pero no sabemos cuantos
		//filtros diferentes tiene el prefijo.
		ArrayList<Position> positionListFinal = new ArrayList<Position>();
		ArrayList<Position> positionList = (ArrayList<Position>) positionListReference.clone();
		ArrayList<Position> positionListLast = null;
		
		for(int i = 0; i<prefix.length(); i++) {
			//Si el prefijo es doble(repetido) se mandan los dos caracteres juntos
			if(i+2 <= prefix.length()) {
				if(prefix.substring(i, i+1).equals(prefix.substring(i+1, i+2))) {
					positionList = directionFilter(prefix.substring(i,i+2), positionList);
					i++;
				}else {
					positionList = directionFilter(prefix.substring(i,i+1), positionList);
				}
			}else {
				positionList = directionFilter(prefix.substring(i,i+1), positionList);
			}
			
			//Si la combinacion de los prefijos es vacia, entonces suponemos un filto nuevo
			//ya que no tiene sentido aplicar un movimiento sin posiciones.
			if(positionList.isEmpty()) {
				for(Position pos : positionListLast) {
					positionListFinal.add(pos);
				}
				positionListLast.clear();
				prefix = prefix.substring(i);
				positionList = (ArrayList<Position>) positionListReference.clone();
				i -= i+1;
			} else {
				positionListLast = (ArrayList<Position>) positionList.clone(); 
			}
		}
		for(Position pos : positionListLast) {
			positionListFinal.add(pos);
		}
		return positionListFinal;
	}
	
	private static Position setFlagPosition(BetzaMove move) {
		Position flagPositionPrototype = new Position();
		//capture
		if(move.prefix.contains("m")) {
			move.prefix = move.prefix.replace("m","");
			flagPositionPrototype.captureFlag = Constants.CAPTURE_NO;
		}else if(move.prefix.contains("c")) {
			move.prefix = move.prefix.replace("c","");
			flagPositionPrototype.captureFlag = Constants.CAPTURE_YES;
		}else {
			flagPositionPrototype.captureFlag = Constants.CAPTURE_BOTH;
		}
		
		//jump
		if(move.prefix.contains("j")) {
			move.prefix = move.prefix.replace("j","");
			flagPositionPrototype.captureFlag = Constants.JUMP_NO;
		}else if(move.prefix.contains("n")) {
			move.prefix = move.prefix.replace("n","");
			flagPositionPrototype.captureFlag = Constants.JUMP_YES;
		}else {
			flagPositionPrototype.captureFlag = Constants.JUMP_BOTH;
		}
		
		//slide jump
		if(move.prefix.contains("p")) {
			move.prefix = move.prefix.replace("p","");
			flagPositionPrototype.captureFlag = Constants.JUMP_SLIDE_CONTINUE;
		}else if(move.prefix.contains("g")) {
			move.prefix = move.prefix.replace("g","");
			flagPositionPrototype.captureFlag = Constants.JUMP_SLIDE_STOP;
		}else {
			flagPositionPrototype.captureFlag = Constants.JUMP_SLIDE_NO;
		}
		
		return flagPositionPrototype;
	}
	
	private static int getMoveType(BetzaMove move) {
		if(move.suffix.length() >0)
			if(move.suffix.charAt(0) == '0') return Constants.SLIDE;
		if(move.atom == 'W' || move.atom == 'F') return Constants.STEP;
		return Constants.JUMP;
	}
	
	private static boolean positionExist(ArrayList<Position> list, int[] pos) {
		boolean exist = false;
		for(Position position : list) {
			if(position.x == pos[0] && position.y == pos[1]) {
				exist = true;
			}
		}
		return exist;
	}
	
	private static boolean positionExist(ArrayList<Position> list, Position pos) {
		int[] intPos = {pos.x,pos.y};
		return positionExist(list, intPos);
	}
}