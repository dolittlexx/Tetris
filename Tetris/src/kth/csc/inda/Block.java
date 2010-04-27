package kth.csc.inda;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Block extends JPanel {


    private int squareX = 0;
    private int squareY = 0;
    private int squareW = 10;
    private int squareH = 10;
    
    private Color fillColor;
    private Color edgeColor;
    
    private short state;

    public Block(short state){
    	this.state = state;
    }
    
    public void setAsBackground(){
    	state = 0;
    	fillColor = Color.gray;
    	edgeColor = Color.black;
    	repaint();
    }
    
    public void setAsBlock(short state){
    	this.state = state;
    	if(state==1)
    		fillColor = Color.RED;
    	else
    		fillColor = Color.CYAN;
    	edgeColor = Color.black;
    	repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(state>0){
	        g.setColor(fillColor);
	        g.fillRect(squareX,squareY,getSize().width,getSize().height);
	        g.setColor(edgeColor);
	        g.drawRect(squareX,squareY,getSize().width,getSize().height);
        }else{
        	g.setColor(fillColor);
	        g.fillRect(squareX,squareY,getSize().width,getSize().height);
	        g.setColor(edgeColor);
	        g.drawRect(squareX,squareY,getSize().width,getSize().height);
        }
    }
}
