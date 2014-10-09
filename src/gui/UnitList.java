package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class UnitList extends JPanel {

	private int width;
	private int height;
	
	public UnitList(int width, int height){
		this.width = width;
		this.height = height;

	//	this.setBackground(Color.red);
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
		g.drawString("UnitList", width/2, height/2);
		
		
		g.drawLine(0, 0, 0, height);
	  }

}
