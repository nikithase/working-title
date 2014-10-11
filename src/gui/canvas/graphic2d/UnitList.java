package gui.canvas.graphic2d;

import gamelogic.gameobjects.Unit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * 
 * This class shows a list of all units on the field
 * 
 * @author Ludwig Biermann
 *
 */
public class UnitList extends JPanel {

	private static final long serialVersionUID = -2885311960920476818L;
	private int width;
	private int height;
	private String player_name;
	private List<Unit> units;

	/**
	 * Creates a new Unit List graphic Interface
	 * 
	 * @param width
	 *            of the Interface
	 * @param height
	 *            of the Interface
	 * @param player_name
	 *            Player Name
	 */
	public UnitList(int width, int height, String player_name) {
		this.width = width;
		this.height = height;
		this.player_name = player_name;
		this.units = new LinkedList<Unit>();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(units.size() != 0){
		g.drawString("Unit List", 0, 0);

		g.drawLine(0, 0, 0, height);

		int size = units.size();

			size = height / size;
			
		

		int count = 0;

		for (Unit u : units) {

			g.drawLine(0, count * size + 1, width, count * size + 1);

			g.drawLine(0, count * size + 1, width, count * size + 1);

			BufferedImage img;

                       if (u.owner.equals(player_name)) {
                           if (u.texture != null && u.texture.equals("baguette_bruiser")) {
                               img = TextureLib.baguette_bruiser;
                           } else {
                               img = TextureLib.wBauer;
                           }
                       } else {
                           if (u.texture != null &&u.texture.equals("baguette_bruiser")) {
                               img = TextureLib.enemy_baguette_bruiser;
                           } else {
                               img = TextureLib.sBauer;
                           }

                       }

			g.drawImage(img, 0, count * size, size, size, this);

			/**
			 * Draw Nummber
			 */

			String id = "" + u.id;

			int midx = 0 + (size / 2) - id.length() / 2 - 3;
			int midy = count * size + (size / 2) + this.getFont().getSize() / 2;

			g.drawString(id, midx, midy);

			/**
			 * Draw Status
			 */

			g.setColor(Color.black);
			Font f = new Font(getFont().getFamily(), getFont().getStyle(), size / 4);
			g.setFont(f);

			g.drawString("Hitpoints: " + u.hitpoints, size + 5, count * size + (size / 3));
			g.drawString("Damage: " + u.damage, size + 5, count * size + (2 * size / 3));
			g.drawString("Movement Speed: " + u.movespeed, size + 5, count * size + (3 * size / 3));

			count++;
		}
		}

	}

	/**
	 * Refresh the shown unit list
	 * 
	 * @param units
	 *            the list of units to be shown
	 */
	public void refresh(List<Unit> units) {

		this.units = units;
		this.repaint();

	}

}
