package gui;

import gamelogic.Unit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class UnitList extends JPanel {

	private int width;
	private int height;
	private String player_name;
	private List<Unit> units;
	
	
	public UnitList(int width, int height, String player_name){
		this.width = width;
		this.height = height;
		this.player_name = player_name;
	//	this.setBackground(Color.red);
		this.units = new LinkedList<Unit>();
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
		g.drawString("Unit List", 0, 0);
		
		
		g.drawLine(0, 0, 0, height);
		
		int size = units.size(); 
		
		size = height / size;
		
		int count = 0;
		
		for(Unit u: units){
			
			

			g.drawLine(0, count * size + 1, width, count * size + 1);

			g.drawLine(0, count * size + 1, width, count * size + 1);
			
			BufferedImage img;
			
			if(u.owner.equals(player_name)){
				img = TextureLib.wBauer;
				
			}else {
				img = TextureLib.sBauer;
			}
			
			
			g.drawImage(img, 0, count * size, size, size, this);
			
			
			Font f = new Font(getFont().getFamily(), getFont().getStyle(), size/4);
			g.setFont(f);
			
			g.drawString("Hitpoints: " + u.hitpoints, size + 5, count * size + (size/3));
			g.drawString("Damage: " + u.damage, size + 5, count * size + (2*size/3));
			g.drawString("Movement Speed: " + u.movespeed, size + 5, count * size + ( 3*size/3));
			
			count++;
		}
		
		
	  }

	public void refresh(List<Unit> units) {
		
		this.units = units;
		this.repaint();
		
	}

}
