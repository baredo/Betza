package runnables;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import betza.Betza;
import logic.Piece;
import utils.Notation;
import view.Screen;

public class menu {

	public static void main(String[] args) {
		Notation.Init();
		Screen scr = new Screen();
		
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		ArrayList<Piece> pieceList = new ArrayList<Piece>();
//		pieceList.add(new Piece("Pawn","fW"));
//		pieceList.add(new Piece("Knight","N"));
//		pieceList.add(new Piece("Shogi_Knight","ffN"));
//		pieceList.add(new Piece("King","WF"));
//		pieceList.add(new Piece("Gold General","WfF"));
//		pieceList.add(new Piece("Silver General","FfW"));
//		pieceList.add(new Piece("Donkey","W2"));
//		pieceList.add(new Piece("Horse General","fFbWfW3"));
//		pieceList.add(new Piece("Fire general","fFvW3"));
//		pieceList.add(new Piece("Buddisth Devil","fF3bsW"));
//		pieceList.add(new Piece("Rook","WW"));
//		pieceList.add(new Piece("Bishop","F0"));
//		pieceList.add(new Piece("Queen","W0F0"));
//		pieceList.add(new Piece("Vertical Mover","WvWW"));
//		pieceList.add(new Piece("Walking heron","sW2fF2vWW"));
//		pieceList.add(new Piece("Nightrider","N2"));
		pieceList.add(new Piece("Test","sWWfWWbFFfF5bW5"));
//		pieceList.add(new Piece("Test","sWWFFvW4"));
//		pieceList.add(new Piece("Rook","fFvW3"));

//		//chess
//		pieceList.add(new Piece("Bishop","B"));
//		pieceList.add(new Piece("Rook","R"));
//		pieceList.add(new Piece("Queen","Q"));
//		pieceList.add(new Piece("King","K"));
//		//chess extended
//		pieceList.add(new Piece("King","KfW2"));
		
		for(Piece piece : pieceList) {
			System.out.println("Name: "+piece.name+"   Betza Notation: "+piece.betzaNotation);
			scr.setGraph(piece.moveList);
			System.out.println();
		}
	}
}
