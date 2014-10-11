package gui.canvas.graphic2d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * a little class who load and holds all graphics
 * 
 * @author Ludwig Biermann
 *
 */
public class TextureLib {

	/**
	 * white Texture for "Weisser Bauer"
	 */
	public static BufferedImage wBauer = null;

	/**
	 * white Texture for "Schwarzer Bauer"
	 */
	public static BufferedImage sBauer = null;
        /**
         * the baguette bruiser
         */
	public static BufferedImage baguette_bruiser = null;
        
         /**
         * enemy baguette bruiser
         */
	public static BufferedImage enemy_baguette_bruiser = null;

	/**
	 * create and initialize a new Texture Library
	 */
	public TextureLib() {
		try {
			wBauer = ImageIO.read(new File("img/canvas2d/wbauer.png"));
			sBauer = ImageIO.read(new File("img/canvas2d/sbauer.png"));
			baguette_bruiser = ImageIO.read(new File("img/canvas2d/baguette_bruiser.png"));
			enemy_baguette_bruiser = ImageIO.read(new File("img/canvas2d/enemy_baguette_bruiser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
