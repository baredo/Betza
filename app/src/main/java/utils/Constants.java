package utils;

public class Constants {
	//position move type
	public final static int ERROR = -2;
	public final static int BLANK = -1;
	public final static int PIECE = 0;
	public final static int STEP = 1;
	public final static int SLIDE = 2;
	public final static int JUMP = 3;
	
	//capture flag, piece only can execute the move if...
	public final static int CAPTURE_BOTH = 0; //no prefix
	public final static int CAPTURE_YES = 1; //'c' prefix
	public final static int CAPTURE_NO = 2; //'m' prefix
	
	//jump flag, piece move is a jump or not
	public final static int JUMP_BOTH = 0; //no prefix
	public final static int JUMP_YES = 1; //'j' jump prefix
	public final static int JUMP_NO = 2; //'n' no jump prefix
	
	//slide jump flag, piece move is a slide jump or not
	public final static int JUMP_SLIDE_NO = 0; //no prefix
	public final static int JUMP_SLIDE_CONTINUE = 1; //'p' jump prefix
	public final static int JUMP_SLIDE_STOP = 2; //'g' no jump prefix
}
