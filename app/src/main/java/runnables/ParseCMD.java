package runnables;
import logic.Piece;
import utils.Notation;
import view.Screen;

public class ParseCMD {

	public static void main(String[] args) {
		Screen scr = new Screen();
		Notation.Init();
		switch(args.length) {
		case 0: System.out.println("Se necesitan añadir el parametro a traducir 'Betza Notation'."); break;
		case 1: scr.setGraph(new Piece("",args[0]).moveList); break;
		}
	}

}
