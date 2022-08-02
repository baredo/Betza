package view;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import utils.Constants;

public class Screen {
	private int w;
	private int h;
	private int pieceX;
	private int pieceY;
	public ArrayList<Position> positionList;
	
	public char symbolBlank = ' ';
	public char symbolPiece = 'P';
	public char symbolStep = 'o';
	public char symbolJump = '*';
	public char symbolSlide = '-';
	
	public boolean fancySlide = true;
	public boolean extendSlide = true;
	public char[] symbolFancySlide = {'|','/','-','\\'};
	//public char[] symbolFancySlide = {'|','/','─','\\'};
	
	public Screen(){
		positionList = new ArrayList<Position>();
	}
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
	public Position[][] screenData; 

	public void setAutomaticSizeScreen() {
		Position pCorner = calculateFrame();
		w = pCorner.x+1;
		h = pCorner.y+1;
		
		screenData = new Position[w][h];
		
		for(int i = 0; i < screenData.length;i++) {
			for (int j = 0; j < screenData[0].length; j++) {
				screenData[i][j] = new Position(i,j,Constants.BLANK);
			}
		}
	}
	
	public void setGraph(ArrayList<Position> positionListExt) {
		positionList = (ArrayList<Position>) positionListExt.clone();
		positionList.add(new Position(0,0,Constants.PIECE));
		
		setAutomaticSizeScreen();
		extendSlider();
		normalizePosition();
		for(Position pos : positionList) {
			screenData[pos.x][pos.y] = pos;
		}
		
		PrintStream out;
		for(int i = screenData[0].length-1; i >= 0 ;i--) {
			for (int j = 0; j < screenData.length ; j++) {
					try {
						out = new PrintStream(System.out, true, "UTF-8");

						
						switch(screenData[j][i].type) {
						case Constants.BLANK: out.print(symbolBlank); break;
						case Constants.STEP: out.print(symbolStep); break;
						case Constants.JUMP: out.print(symbolJump); break;
						case Constants.SLIDE: if(fancySlide) PrintFancySlider(j,i, out); else out.print(symbolSlide); break;
						case Constants.PIECE: out.print(symbolPiece); break;
						}
						} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				
			}
			System.out.println();
		}
	}
	
	private void PrintFancySlider(int x, int y, PrintStream out) {
		
		if(x == pieceX)
			out.print(symbolFancySlide[0]);
		else if(x == y-pieceY+pieceX)
			out.print(symbolFancySlide[1]);
		else if(y == pieceY)
			out.print(symbolFancySlide[2]);
		else if(x == (y-pieceY)*-1+pieceX)
			out.print(symbolFancySlide[3]);
	}
	
	private void extendSlider() {
		ArrayList<Position> positionListBuffer = new ArrayList<Position>();
		for(Position pos : positionList) {
			if(extendSlide && pos.type == Constants.SLIDE) {
				int i = 1;
				while(pos.x*i >= 0-pieceX && pos.x*i < w-pieceX && pos.y*i >= 0-pieceY && pos.y*i < h-pieceY) {
					positionListBuffer.add(new Position(pos.x*i,pos.y*i,Constants.SLIDE));
					i++;
				}
			}
		}
		for(Position pos : positionListBuffer) {
			positionList.add(pos);
		}
	}
	
	private void normalizePosition() {
		
		for(Position position : positionList) {
			position.x += pieceX;
			position.y += pieceY;
		}
	}
	
	private Position calculateFrame(){
		int minX = 100;
		int minY = 100;
		int maxX = 0;
		int maxY = 0;
		
		for(Position position : positionList) {
			if(position.x < minX) minX = position.x;
			if(position.y < minY) minY = position.y;
			
			if(position.x > maxX) maxX = position.x;
			if(position.y > maxY) maxY = position.y;
		}
		
		pieceX = 0-minX;
		pieceY = 0-minY;

		return new Position(maxX+pieceX,maxY+pieceY);
	}
}
