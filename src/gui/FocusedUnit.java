package gui;

import gamelogic.Unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class FocusedUnit extends JPanel {

	private int width;
	private int height;

	private Unit u;

	private String player;

	public FocusedUnit(int width, int height, String player) {
		this.width = width;
		this.height = height;
		this.player = player;

		// this.setBackground(Color.blue);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	//	g.drawString("FocusedUnit", width / 2, height / 2);

		g.drawLine(0, 0, width, 0);

		if (u != null) {

			BufferedImage img;
			if (u.owner == player) {
				img = TextureLib.wBauer;
			} else {
				img = TextureLib.sBauer;

			}

			g.drawImage(img, 0, 0, height, height, this);

			g.drawString("Hitpoints: " + u.hitpoints, height + 10, 1 * height / 6);
			g.drawString("Damage: " + u.damage, height + 10, 2 * height/ 6);
			g.drawString("Movement Speed: " + u.movespeed, height + 10, 3 * height/6);
		}

	}

	public void showUnit(Unit tmp) {
		this.u = tmp;
		this.repaint();
	}

}
