package gui;

import gamelogic.Unit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Gamefield extends JPanel {

	private int width;
	private int height;
	
	private int amount_x;
	private int amount_y;
	
	private int tile_size;
	
	private List<Unit> units;
	
	private String player;
	
	public Gamefield(int width, int height, int amount_x, int amount_y, int tile_size, String player){
		this.width = width;
		this.height = height;
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.tile_size = tile_size;
		this.player = player;
		
		this.units = new LinkedList<Unit>();
		//this.setBackground(Color.black);
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
//		g.drawString("Gamefield", width/2, height/2);
		
		g.setColor(Color.lightGray);
		
		for(int x = 0; x < amount_x; x++){
			for(int y = 0; y < amount_y; y++){
			    
				if((y%2 == 1 && x%2 == 0) || (y%2 == 0 && x%2 == 1)){
					g.drawRect(x * tile_size, y * tile_size, tile_size, tile_size);
				} else {
					g.fillRect(x * tile_size, y * tile_size, tile_size, tile_size);
				}
			}
		}
		
		for(Unit u: units){
			if(player.equals(u.owner)){
				g.drawImage(TextureLib.wBauer, u.posX * tile_size, u.posY * tile_size, this);
			} else {
				g.drawImage(TextureLib.sBauer, u.posX * tile_size, u.posY * tile_size, this);
			}
		}
		
	  }

	/**
	 * 
	 * @param units
	 */
	public void refresh(List<Unit> units) {
		this.units = units;
		this.repaint();
	}

}
