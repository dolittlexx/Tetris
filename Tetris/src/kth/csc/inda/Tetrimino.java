package kth.csc.inda;
import java.util.Random;

public class Tetrimino {
	
	private short[][] matrix;
	private static final int teal = 1;
	private short[][] temp;
	
	

	
	public Tetrimino(){
		matrix = new short[4][4];
		temp = new short[4][4];
	}
	
	private void create1(){
		matrix[2][0] = 1; 
		matrix[2][1] = 1;
		matrix[2][2] = 1;
		matrix[1][1] = 1;
	}
	public void create2(){
		matrix[2][0] = 1; 
		matrix[2][1] = 1;
		matrix[2][2] = 1;
		matrix[2][3] = 1;
	}
	
	public void create(){
		Random generator = new Random();
		int value = generator.nextInt(2)+1;
		switch(value){
		case 1: create1();break;
		case 2: create2();break;
		}
		
	}
	
	
	public void rotateRight(){
		for(int i=0; i<4; i++){
			for (int j=0; j<4; j++){
				temp[j][3-i] = matrix[i][j];
			}
		}
		copy();
	}
	
	public String toString(){
		String s="";
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				s+=matrix[i][j]+" ";
			}
			s+="\n";
		}
		return s;
	}
	
	public void copy(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				matrix[i][j] = temp[i][j];
			}
		}
	}
	
	
	public static void main(String args[]){
		Tetrimino tet = new Tetrimino();
		tet.create1();
		System.out.println(tet);
		tet.create2();
		System.out.println(tet);
	}
	
	
	public short[][] getMatrix() {
			return matrix;
		}
	
		public void setMatrix(short[][] matrix) {
			this.matrix = matrix;
		}

}