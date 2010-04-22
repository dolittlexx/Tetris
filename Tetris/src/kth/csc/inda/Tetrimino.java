package kth.csc.inda;

public class Tetrimino {
	
	private int[][] matrix;
	private int[] middle;
	private static final int teal = 1;
	
	public Tetrimino(){
		matrix = new int[4][4];
		middle = new int[2];
	}
	
	public void create1(){
		matrix[1][0] = teal; 
		matrix[1][1] = teal;
		matrix[1][2] = teal;
		matrix[1][3] = teal;
		middle[0] = 1;
		middle[1] = 1;
	}

}
