package gui;

import gamelogic.Unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import com.sun.javafx.geom.Rectangle;

public class Gamefield extends JPanel implements MouseListener {

	private int width;
	private int height;
	
	private int amount_x;
	private int amount_y;
	
	private int tile_size;
	
	private List<CellUnit> units;
	private List<Cell> l_unit_path;
	private int l_unit;
	
	private String player;
	
	private final static Color BLACK_CELLS = Color.lightGray;
	private final static Color LOCKED_CELLS = new Color(0, 0, 255, 80);;
	
	public Gamefield(int width, int height, int amount_x, int amount_y, int tile_size, String player){
		this.width = width;
		this.height = height;
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.tile_size = tile_size;
		this.player = player;
		
		this.units = new LinkedList<CellUnit>();
		//this.setBackground(Color.black);
		
		l_unit = -1;
		
		this.addMouseListener(this);
	}
	
	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
//		g.drawString("Gamefield", width/2, height/2);
		
		g.setColor(BLACK_CELLS);
		
		for(int x = 0; x < amount_x; x++){
			for(int y = 0; y < amount_y; y++){
			    
				if((y%2 == 1 && x%2 == 0) || (y%2 == 0 && x%2 == 1)){
					g.drawRect(x * tile_size, y * tile_size, tile_size, tile_size);
				} else {
					g.fillRect(x * tile_size, y * tile_size, tile_size, tile_size);
				}
			}
		}
		
		for(CellUnit u: units){
			if(player.equals(u.owner)){
				g.drawImage(TextureLib.wBauer, u.r.x,u.r.y, this);
			} else {
				g.drawImage(TextureLib.sBauer, u.r.x, u.r.y, this);
			}
		}
		
		if(l_unit != -1){
			CellUnit tmp = getCellUnit(l_unit);
			if(tmp != null){
				g.setColor(LOCKED_CELLS);
				g.fillRect(tmp.r.x, tmp.r.y, tmp.r.width, tmp.r.height);
				
				for(Cell c:l_unit_path){
					g.fillRect(c.x, c.y, tile_size, tile_size);
				}
			}
			
			
		}
		
	  }

	/**
	 * returns a List of Cells for a possible Path
	 * @param u
	 * @return
	 */
	private List<Cell> computePossiblePath(CellUnit u){
		
		List<Cell> cors = new LinkedList<Cell>();
		
		
		int startx = u.r.x - (u.speed * tile_size);
		int starty = u.r.y - (u.speed * tile_size);

		int endx = u.r.x + (u.speed * tile_size);
		int endy = u.r.y + (u.speed * tile_size);
		
		

		for(int x = startx; x <= endx; x = x + tile_size){
			for(int y = starty; y <= endy; y = y + tile_size){
				
				System.out.println("test");
				
				if(getUnitID(x, y) == -1){
					cors.add(new Cell(x,y));
				}
			}
		}
		
		System.out.println("Possible Paths: " + cors.size());
		
		return cors;
	}
	
	private CellUnit getCellUnit(int id){
		for(CellUnit u: units){
			if(u.id == id){
				return u;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param units
	 */
	public void refresh(List<Unit> units) {
		this.units = convertUnits(units);
		this.repaint();
	}
	
	
	private List<CellUnit> convertUnits(List<Unit> units){
		
		LinkedList<CellUnit> tmp = new LinkedList<CellUnit>();
		
		for(Unit u: units) {
			tmp.add(new CellUnit(new Rectangle(u.posX * tile_size, u.posY * tile_size ,tile_size ,tile_size),u.id, u.owner, u.movespeed));
		}
		
		return tmp;
	}
	
	private int getUnitID(int x, int y){
		
		for(CellUnit u: units){
			if(u.r.contains(x, y)) {
				return u.id;
			}
		}
		
		
		return -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		l_unit = -1;
		
		System.out.println("Mouse: click on Gamefield: " + e.getX() + "x" + e.getY());
		
		int id = getUnitID(e.getX(), e.getY());
		
		if(id != -1){
			
			l_unit = id;
			l_unit_path = this.computePossiblePath(this.getCellUnit(l_unit));
			
		}
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse: pressed on Gamefield");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse: released on Gamefield");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse: enterd on Gamefield");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private class CellUnit{
		
		public int id;
		public Rectangle r;
		public String owner;
		public int speed;
		
		public CellUnit(Rectangle r, int id, String owner, int speed){
			this.id = id;
			this.r = r;
			this.owner = owner;
			this.speed = speed;
		}
		
	}
	
	private class Cell{
		public int x;
		public int y;
		
		public Cell(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
}
