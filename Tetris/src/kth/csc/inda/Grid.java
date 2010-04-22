package kth.csc.inda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid extends JPanel{
	
	private int[][] grid;
	private static final int xSize = 20;
	private static final int ySize = 20;
	private static final int fallingSpeed = 1;
	private JFrame frame;
	private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;

    
	public Grid(){
		grid = new int[xSize][ySize];
		frame = new JFrame();
		frame.setSize(200, 200);
		frame.setVisible(true);
		Graphics2D g2;
		repaint();
	}
	
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
    }  

	

}
