package kth.csc.inda;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Grid{

    private short[][] grid;
    private short[][] temp;
    private Tetrimino tetrimino1;
    private static final int rows = 20;
    private static final int cols = 20;
    private static final int hgap = 0;
    private static final int vgap = 0;
    private static final int fallingSpeed = 1;

    private JFrame frame;
    private GridLayout gridLayout;
    private Block[][] blocks;


    public Grid(){

        grid = new short[rows][cols];
        temp = new short[rows][cols];
        blocks = new Block[rows][cols];
        frame = new JFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(cols*20, rows*20);
        
        gridLayout = new GridLayout(rows, cols, hgap, vgap);
        frame.setLayout(gridLayout);
         
        initGrid();
        frame.setVisible(true);
        repaintGrid(grid);
    }
    
    public void initGrid(){
    	for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
            	Block block = new Block((short)0);
                blocks[i][j] = block;
                frame.add(block);
                grid[i][j] = 0;
                block.repaint();
            }
        }
    }
    
    public void repaintGrid(short[][] grid){
    	this.grid = grid;
    	for(int i=0; i<rows; i++){
    		for(int j=0; j<cols; j++){
    			if(grid[i][j]>0){
    				blocks[i][j].setAsBlock(grid[i][j]);
    			}else{
    				blocks[i][j].setAsBackground();
    			}
    		}
    	}
    	
    }
	
    public boolean paintTetrimino(int xpos, int ypos, short[][] tetMatrix){
    	if(xpos>cols || xpos<-2 || ypos>rows-2){
    		System.out.println("out of bounds");
    		return false;
    	}
    	
    	copyToTemp();
    	
    	if(xpos>(cols-4)){
			for(int k=0; k<4; k++){
				if(tetMatrix[k][3] != 0){
					System.out.println("första");
					return false;
				}else{
					tetMatrix[k][3]= tetMatrix[k][2];
					tetMatrix[k][2]= tetMatrix[k][1];
					tetMatrix[k][1]= tetMatrix[k][0];
					tetMatrix[k][0]= 0;
				}
			}
			paintTetrimino(xpos-1,ypos,tetMatrix);
		}
    	
    	
    	else if(xpos<0){		
			for(int k=0; k<4; k++){
				if(tetMatrix[k][0] != 0){
					System.out.println("andra");
					return false;
				}else{
					tetMatrix[k][0]= tetMatrix[k][1];
					tetMatrix[k][1]= tetMatrix[k][2];
					tetMatrix[k][2]= tetMatrix[k][3];
					tetMatrix[k][3]= 0;
				}
			}
			paintTetrimino(xpos+1,ypos,tetMatrix);
		}
    	
    	else if(ypos>rows-4){
    		System.out.println("rows-4");
    		for(int k=0; k<4; k++){
				if(tetMatrix[3][k] != 0){
					System.out.println("yförsta");
					return false;
				}else{
					tetMatrix[3][k]= tetMatrix[2][k];
					tetMatrix[2][k]= tetMatrix[1][k];
					tetMatrix[1][k]= tetMatrix[0][k];
					tetMatrix[0][k]= 0;
				}
			}
			paintTetrimino(xpos,ypos-1,tetMatrix);
    	}
    	
    	else if(ypos<0){
    		for(int k=0; k<4; k++){
				if(tetMatrix[0][k] != 0){
					System.out.println("yandra");
				}else{
					tetMatrix[0][k]= tetMatrix[1][k];
					tetMatrix[1][k]= tetMatrix[2][k];
					tetMatrix[2][k]= tetMatrix[3][k];
					tetMatrix[3][k]= 0;
				}
			}
			paintTetrimino(xpos,ypos+1,tetMatrix);
    	}
	    return placeTetrimino(xpos,ypos,tetMatrix);
    }
   

    public boolean placeTetrimino(int xpos, int ypos, short[][] tetMatrix){
    	for(int i=0; i<4; i++){
    		for(int j=0; j<4; j++){
    			if(grid[ypos+i][xpos+j]==0){
    				grid[ypos+i][xpos+j] = tetMatrix[i][j];
    			}else{
    				System.out.println("tredje");
    				return false;
    			}
    		}
    	}
    	repaintGrid(grid);
    	return true;
    }
    
    public void copyToTemp(){
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				temp[i][j] = grid[i][j];
			}
		}
	}
   public void copyToMatrix(){
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				grid[i][j] = temp[i][j];
			}
		}
	}
   public void remove(){
	   for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				if(grid[i][j] == 1){
					grid[i][j]=0;
				}
			}
		}
   }
   
   public short[][] getGrid(){
	   return grid;
   }
   
   
   
  public static void main(String args[]){
       Grid grid = new Grid();
       Tetrimino tet = new Tetrimino();
       tet.create();  
       //tet.rotateRight();
       FallingThread ft = new FallingThread((short) 1,grid);
       ft.start();      
   }
  
  
}
