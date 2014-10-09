package gui;

import gamelogic.Command;
import gamelogic.Gamelogic;
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
	private List<Rectangle> l_unit_path;
	private List<Rectangle> l_e_unit_path;
	private int l_unit;

	private String player;

	private final static Color BLACK_CELLS = Color.lightGray;
	private final static Color LOCKED_CELLS = new Color(0, 0, 255, 90);
	private final static Color LOCKED_CELLS_PATH = new Color(0, 0, 255, 50);

	private Gamelogic logic;

	public Gamefield(int width, int height, int amount_x, int amount_y, int tile_size, String player, Gamelogic logic) {
		this.width = width;
		this.height = height;
		this.amount_x = amount_x;
		this.amount_y = amount_y;
		this.tile_size = tile_size;
		this.player = player;

		this.units = new LinkedList<CellUnit>();
		this.l_unit_path = new LinkedList<Rectangle>();
		this.l_e_unit_path = new LinkedList<Rectangle>();
		// this.setBackground(Color.black);

		l_unit = -1;
		this.logic = logic;

		this.addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawString("Gamefield", width/2, height/2);

		g.setColor(BLACK_CELLS);

		for (int x = 0; x < amount_x; x++) {
			for (int y = 0; y < amount_y; y++) {

				if ((y % 2 == 1 && x % 2 == 0) || (y % 2 == 0 && x % 2 == 1)) {
					g.drawRect(x * tile_size, y * tile_size, tile_size, tile_size);
				} else {
					g.fillRect(x * tile_size, y * tile_size, tile_size, tile_size);
				}
			}
		}

		for (CellUnit u : units) {
			if (player.equals(u.owner)) {
				g.setColor(Color.black);
				g.drawImage(TextureLib.wBauer, u.r.x, u.r.y, this);
			} else {
				g.setColor(Color.white);
				g.drawImage(TextureLib.sBauer, u.r.x, u.r.y, this);
			}
			
			/**
			 * Draw Nummber
			 */
			
			String id = "" + u.id;
			
			int midx = u.r.x + (tile_size/2) - id.length()/2 - 3;
			int midy = u.r.y + (tile_size/2) + this.getFont().getSize()/2;
			
			g.drawString(id, midx, midy);
		}

		if (l_unit != -1) {
			CellUnit tmp = getCellUnit(l_unit);
			if (tmp != null) {
				g.setColor(LOCKED_CELLS);
				g.fillRect(tmp.r.x, tmp.r.y, tmp.r.width, tmp.r.height);

				g.setColor(LOCKED_CELLS_PATH);
				for (Rectangle c : l_unit_path) {
					g.fillRect(c.x, c.y, tile_size, tile_size);
				}
			}

		}

	}

	/**
	 * returns a List of Cells for a possible Path
	 * 
	 * @param u
	 */
	private void computePossiblePath(CellUnit u) {

		l_unit_path = new LinkedList<Rectangle>();
		l_e_unit_path = new LinkedList<Rectangle>();

		int startx = u.r.x - (u.speed * tile_size);
		int starty = u.r.y - (u.speed * tile_size);

		int endx = u.r.x + (u.speed * tile_size);
		int endy = u.r.y + (u.speed * tile_size);

		for (int x = startx; x <= endx; x = x + tile_size) {
			for (int y = starty; y <= endy; y = y + tile_size) {

				System.out.println("test");

				if (getUnitID(x, y) == -1) {
					l_unit_path.add(new Rectangle(x, y, tile_size, tile_size));
				} else {
					System.out.print( getUnitID(x,y)  + "x" +l_unit);
					if(getUnitID(x,y) != l_unit){
						l_e_unit_path.add(new Rectangle(x, y, tile_size, tile_size));
					}
				}
			}
		}

		System.out.println("Possible Paths: " + l_unit_path.size());
		System.out.println("Possible Enemys: " + l_e_unit_path.size());

	}

	private CellUnit getCellUnit(int id) {
		for (CellUnit u : units) {
			if (u.id == id) {
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

	private List<CellUnit> convertUnits(List<Unit> units) {

		LinkedList<CellUnit> tmp = new LinkedList<CellUnit>();

		for (Unit u : units) {
			tmp.add(new CellUnit(new Rectangle(u.posX * tile_size, u.posY * tile_size, tile_size, tile_size), u.id, u.owner, u.movespeed));
		}

		return tmp;
	}

	private int getUnitID(int x, int y) {

		for (CellUnit u : units) {
			if (u.r.contains(x, y)) {
				return u.id;
			}
		}

		return -1;
	}

	private boolean containpath(int x, int y) {
		for (Rectangle c : l_unit_path) {
			if (c.contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	private boolean containEpath(int x, int y) {
		for (Rectangle c : l_e_unit_path) {
			if (c.contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	private boolean checkOwnership(int id) {

		for (CellUnit u : units) {
			if (u.id == id) {
				if (u.owner.equals(player)) {
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse: pressed on Gamefield");

	}

	
	/**
	 * TEST ZWECKE
	 * @param canvas
	 */
	CanvasGraphic2D canvas;
	
	public void setCanvas(CanvasGraphic2D canvas){
		this.canvas = canvas;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse: released on Gamefield");
		

		if (l_unit != -1 && l_unit_path.size() != 0) {
			if (containpath(e.getX(), e.getY())) {

				int x = e.getX() / tile_size;
				int y = e.getY() / tile_size;

				if (checkOwnership(l_unit)) {

					Command c = new Command(Command.MOVE, l_unit, x, y);
					System.out.println(c.toString());
					logic.executeCommand(c);

					/** test umgebung **/
					this.canvas.refresh();
					
					
				} else {
					System.out.println("not your unit!");
					repaint();
				}

			}

			if (containEpath(e.getX(), e.getY())) {

				int x = e.getX() / tile_size;
				int y = e.getY() / tile_size;

				if (checkOwnership(l_unit)) {

					Command c = new Command(Command.ATTACK, l_unit, x, y);
					System.out.println(c.toString());
					logic.executeCommand(c);

					/** test umgebung **/
					this.canvas.refresh();
					
					
				} else {
					System.out.println("not your unit!");
					repaint();
				}
			}

			l_unit = -1;

		} else {

			l_unit = -1;

			System.out.println("Mouse: click on Gamefield: " + e.getX() + "x" + e.getY());

			int id = getUnitID(e.getX(), e.getY());

			if (id != -1) {

				l_unit = id;
				this.computePossiblePath(this.getCellUnit(l_unit));

			}

			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse: enterd on Gamefield");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private class CellUnit {

		public int id;
		public Rectangle r;
		public String owner;
		public int speed;

		public CellUnit(Rectangle r, int id, String owner, int speed) {
			this.id = id;
			this.r = r;
			this.owner = owner;
			this.speed = speed;
		}

	}

}
