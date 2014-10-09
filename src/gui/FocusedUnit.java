package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FocusedUnit extends JPanel {


	private int width;
	private int height;
	
	public FocusedUnit(int width, int height){
		this.width = width;
		this.height = height;

	//	this.setBackground(Color.blue);
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
		g.drawString("FocusedUnit", width/2, height/2);
		
		

		g.drawLine(0, 0, width, 0);
	  }

}
