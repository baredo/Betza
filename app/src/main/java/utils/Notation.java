package utils;
public class Notation {
	private final static char[] basic = {'O','U','W','F','D','N','A','C','Z'};
	private final static char[] basicChess = {'B','R','Q','K'};
	private static char[] basicComplete;
	
	private final static char[] suffix = {'0','1','2','3','4','5','6','7','8','9','@'};
	
	private final static int[][] posW = {{0,1},{1,0},{-1,0},{0,-1}};
	private final static int[][] posF = {{1,1},{1,-1},{-1,1},{-1,-1}};
	private final static int[][] posN = {{2,1},{1,2},{-1,2},{-2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
	private final static int[][] posA = {{-2,2},{2,2},{-2,-2},{2,-2}};
	private final static int[][] posD = {{2,0},{0,2},{-2,0},{0,-2}};
	private final static int[][] posG = {{-3,3},{3,3},{-3,-3},{3,-3}};
	private final static int[][] posZ = {{-2,3},{2,3},{-2,2},{2,2},{-2,-2},{2,-2},{-2,-3},{2,-3}};
	private final static int[][] posC = {{-1,3},{1,3},{-1,2},{1,2},{-1,-2},{1,-2},{-1,-3},{1,-3}};
	private final static int[][] posH = {{3,0},{0,3},{-3,0},{0,-3}};
	
	
	public Notation(){
		
	}
	
	public static void Init() {
		StringBuilder sb = new StringBuilder();
		sb.append(basic);
		sb.append(basicChess);
		basicComplete = sb.toString().toCharArray();
	}
	
	public static char[] getBasic() {
		return basicComplete;
	}
	
	public static char[] getSuffix() {
		return suffix;
	}
	
	public static int[][] getPosW(){
		return posW;
	}
	
	public static int[][] getPosF(){
		return posF;
	}
	
	public static int[][] getPosN() {
		return posN;
	}
	
	public static int[][] getPosA(){
		return posA;
	}
	
	public static int[][] getPosD() {
		return posD;
	}
	
	public static int[][] getPosG(){
		return posG;
	}
	
	public static int[][] getPosZ() {
		return posZ;
	}
	
	public static int[][] getPosC(){
		return posC;
	}
	
	public static int[][] getPosH() {
		return posH;
	}

}
