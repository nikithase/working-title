package gui.canvas.graphic2d;

import gamelogic.gameobjects.Unit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 *
 * This class shows a specific Unit
 *
 * @author Ludwig Biermann
 *
 */
public class FocusedUnit extends JPanel {

    private static final long serialVersionUID = 1572147575897209920L;
    private int width;
    private int height;
    private Unit unit;
    private String player;

    /**
     * Creates a new FocusUnit graphic Interface
     *
     * @param width the width of the interface
     * @param height the height of the interface
     * @param player the name of the player
     */
    public FocusedUnit(int width, int height, String player) {
        this.width = width;
        this.height = height;
        this.player = player;

        // this.setBackground(Color.blue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawString("FocusedUnit", width / 2, height / 2);

        g.drawLine(0, 0, width, 0);

        if (unit != null) {

            BufferedImage img;
            if (unit.owner.equals(player)) {
                if (unit.texture.equals("baguette_bruiser")) {
                    img = TextureLib.baguette_bruiser;
                } else {
                    img = TextureLib.wBauer;
                }
            } else {
                if (unit.texture.equals("baguette_bruiser")) {
                    img = TextureLib.enemy_baguette_bruiser;
                } else {
                    img = TextureLib.sBauer;
                }

            }

            g.drawImage(img, 0, 0, height, height, this);

            g.drawString("Hitpoints: " + unit.hitpoints, height + 10, 1 * height / 6);
            g.drawString("Damage: " + unit.damage, height + 10, 2 * height / 6);
            g.drawString("Movement Speed: " + unit.movespeed, height + 10, 3 * height / 6);
        }

    }

    /**
     * shows a new unit
     *
     * @param unit the unit to show
     */
    public void showUnit(Unit unit) {
        this.unit = unit;
        this.repaint();
    }

}
