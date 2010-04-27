package kth.csc.inda;

public class FallingThread extends Thread{
	private Grid grid;
	private boolean free;
	
	public FallingThread(short player, Grid grid){
		this.grid=grid;
	}
	
	public void run(){
		Tetrimino tet = new Tetrimino();
		free = true;
	    tet.create();  
	    short[][] tetMatrix = tet.getMatrix();
	    int y=0;
	    while(free){
	    	grid.remove();
	    	free = grid.paintTetrimino(5,y, tet.getMatrix());
	    	System.out.println(free);
		    try {
		    	System.out.println("y: "+y);
				sleep(1000);
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    y+=1;
		    
		}
	}	
}
