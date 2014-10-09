package gui.canvas.graphic2d;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gamelogic.Unit;
import gui.CanvasGraphic2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import client.Client;

import com.sun.javafx.geom.Rectangle;

/**
 * 
 * This class creates the graphical gamefield
 * 
 * @author Ludwig Biermann
 *
 */
public class Gamefield extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1436708090503344352L;
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
	private Client client;
	private FocusedUnit focused;

	/**
	 * 
	 * Creates a new Gamefield
	 * 
	 * @param amount_x
	 *            Amount of tiles in x direction
	 * @param amount_y
	 *            Amount of tiles in y direction
	 * @param tile_size
	 *            the size of the tiles in pixel
	 * @param player
	 *            name of the player
	 * @param logic
	 *            the game logic
	 * @param focused
	 *            the gui to show a unit
	 * @param client
	 *            needed to send commands
	 */
	public Gamefield(int amount_x, int amount_y, int tile_size, String player, Gamelogic logic, FocusedUnit focused, Client client) {

		this.client = client;

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

		this.focused = focused;
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

			int midx = u.r.x + (tile_size / 2) - id.length() / 2 - 3;
			int midy = u.r.y + (tile_size / 2) + this.getFont().getSize() / 2;

			g.drawString(id, midx, midy);
		}

		if (l_unit != -1) {
			CellUnit tmp = getCellUnit(l_unit);
			if (tmp != null) {
				focused.showUnit(tmp);
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
	private void computePossibleCellActions(CellUnit u) {

		l_unit_path = new LinkedList<Rectangle>();
		l_e_unit_path = new LinkedList<Rectangle>();

		int startx = u.r.x - (u.movespeed * tile_size);
		int starty = u.r.y - (u.movespeed * tile_size);

		int endx = u.r.x + (u.movespeed * tile_size);
		int endy = u.r.y + (u.movespeed * tile_size);

		for (int x = startx; x <= endx; x = x + tile_size) {
			for (int y = starty; y <= endy; y = y + tile_size) {

				System.out.println("test");

				int id = getUnitID(x, y);

				if (id == -1) {
					l_unit_path.add(new Rectangle(x, y, tile_size, tile_size));
				} else {
					System.out.print(getUnitID(x, y) + "x" + l_unit);
					if (!getCellUnit(id).owner.equals(player)) {
						l_e_unit_path.add(new Rectangle(x, y, tile_size, tile_size));
					}
				}
			}
		}

		System.out.println("Possible Paths: " + l_unit_path.size());
		System.out.println("Possible Enemys: " + l_e_unit_path.size());

	}

	/**
	 * returns a Cell Unit by id
	 * 
	 * @param id
	 * @return
	 */
	private CellUnit getCellUnit(int id) {
		for (CellUnit u : units) {
			if (u.id == id) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Refresh the gamefield
	 * 
	 * @param units
	 */
	public void refresh(List<Unit> units) {
		this.units = convertUnits(units);
		CellUnit tmp = this.getCellUnit(l_unit);
		if (tmp != null) {
			this.computePossibleCellActions(tmp);

		}
		this.repaint();
	}

	/**
	 * Extend a Unit by an Rectangle
	 * 
	 * @param units
	 * @return
	 */
	private List<CellUnit> convertUnits(List<Unit> units) {

		LinkedList<CellUnit> tmp = new LinkedList<CellUnit>();

		for (Unit u : units) {
			tmp.add(new CellUnit(new Rectangle(u.posX * tile_size, u.posY * tile_size, tile_size, tile_size), u));
		}

		return tmp;
	}

	/**
	 * returns a Unit id by his coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int getUnitID(int x, int y) {

		for (CellUnit u : units) {
			if (u.r.contains(x, y)) {
				return u.id;
			}
		}

		return -1;
	}

	/**
	 * check if a cell contains the coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean containCellPath(int x, int y) {
		for (Rectangle c : l_unit_path) {
			if (c.contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * check if a enemy cell contains the coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean containEnemyCell(int x, int y) {
		for (Rectangle c : l_e_unit_path) {
			if (c.contains(x, y)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * checks ownership
	 * 
	 * @param id
	 * @return
	 */
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
	 * 
	 */
	CanvasGraphic2D canvas;

	/**
	 * Test ZWECKE
	 * 
	 * @param canvas
	 */
	public void setCanvas(CanvasGraphic2D canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse: released on Gamefield");
		boolean attack = false;

		if (l_unit != -1 && l_unit_path.size() != 0) {
			if (containCellPath(e.getX(), e.getY())) {

				int x = e.getX() / tile_size;
				int y = e.getY() / tile_size;

				if (checkOwnership(l_unit)) {

					Command c = new Command(Command.MOVE, l_unit, x, y);
					System.out.println(c.toString());

					if (client == null) {
						/** test umgebung **/
						logic.executeCommand(c);
						this.canvas.refresh();
					} else {
						client.sendCommand(c);
					}

				} else {
					System.out.println("not your unit!");
					repaint();
				}

			}

			if (containEnemyCell(e.getX(), e.getY())) {

				int x = e.getX() / tile_size;
				int y = e.getY() / tile_size;

				if (checkOwnership(l_unit)) {

					attack = true;

					Command c = new Command(Command.ATTACK, l_unit, x, y);
					System.out.println(c.toString());

					if (client == null) {
						/** test umgebung **/
						logic.executeCommand(c);
						this.canvas.refresh();
					} else {
						client.sendCommand(c);
					}

				} else {
					System.out.println("not your unit!");
					repaint();
				}
			}

		}

		if (!attack) {
			l_unit = -1;

			System.out.println("Mouse: click on Gamefield: " + e.getX() + "x" + e.getY());

			int id = getUnitID(e.getX(), e.getY());

			if (id != -1) {

				l_unit = id;
				this.computePossibleCellActions(this.getCellUnit(l_unit));

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

	private class CellUnit extends Unit {

		public Rectangle r;

		public CellUnit(Rectangle r, Unit u) {
			super(u.id, u.hitpoints, u.posX, u.posY, u.movespeed, u.damage, u.owner);
			this.r = r;
		}

	}

}
