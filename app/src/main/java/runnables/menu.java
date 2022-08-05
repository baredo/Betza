package runnables;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import betza.Betza;
import logic.Piece;
import utils.Notation;
import view.Screen;

public class menu {

	public static void main(String[] args) {
		Notation.Init();
		Screen scr = new Screen();
		ArrayList<Piece> pieceList = new ArrayList<Piece>();
		
		try {
			File file = new File("input.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if(data.length() > 0)
				if(!(data.charAt(0) == '/' && data.charAt(1) == '/')){
					String[] pieceString = data.split(",");
					pieceList.add(new Piece(pieceString[0],pieceString[1]));
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo de input.");
			e.printStackTrace();
		}
		
		for(Piece piece : pieceList) {
			System.out.println("Name: "+piece.name+"   Betza Notation: "+piece.betzaNotation);
			scr.setGraph(piece.moveList);
			System.out.println();
		}
	}
}
